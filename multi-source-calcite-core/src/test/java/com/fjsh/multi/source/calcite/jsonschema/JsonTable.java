package com.fjsh.multi.source.calcite.jsonschema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.rel.type.RelDataTypeFactory;
import org.apache.calcite.rel.type.RelProtoDataType;
import org.apache.calcite.schema.impl.AbstractTable;
import org.apache.calcite.sql.type.SqlTypeName;
import org.apache.calcite.util.Pair;
import org.apache.calcite.util.Source;
import org.codehaus.commons.nullanalysis.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class JsonTable extends AbstractTable {
    private static final Logger logger = LoggerFactory.getLogger(JsonTable.class);

    protected final Source source;
    protected final RelProtoDataType protoRowType;
    protected List<JsonFieldType> fieldTypes;

    public JsonTable(Source source, RelProtoDataType protoRowType) {
        this.source = source;
        this.protoRowType = protoRowType;
    }

    @Override
    public RelDataType getRowType(RelDataTypeFactory typeFactory) {
        if (protoRowType != null) {
            return protoRowType.apply(typeFactory);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode schemaRoot;
        try {
            schemaRoot = objectMapper.readValue(source.file(), ObjectNode.class);
        } catch (IOException e) {
            logger.error("failed to load table schema from " + source, e);
            return null;
        }
        ArrayNode fields = (ArrayNode) schemaRoot.get("fields");
        Iterator<JsonNode> iterator = fields.iterator();
        ArrayList<String> names = new ArrayList<>(16);
        ArrayList<RelDataType> types = new ArrayList<>(16);
        while (iterator.hasNext()) {
            ObjectNode objectNode = (ObjectNode) iterator.next();
            if (!objectNode.has("columnName") || !objectNode.has("dataType")) {
                logger.error("invalid format for field {}, skipped", objectNode.asText());
                continue;
            }
            String field = objectNode.get("columnName").asText();
            names.add(field);
            String type = objectNode.get("dataType").asText();
            switch (type.trim().toLowerCase()){
                case "int":
                    types.add(typeFactory.createSqlType(SqlTypeName.INTEGER));
                    break;
                case "long":
                    types.add(typeFactory.createSqlType(SqlTypeName.BIGINT));
                    break;
                case "double":
                    types.add(typeFactory.createSqlType(SqlTypeName.DOUBLE));
                    break;
                case "float":
                    types.add(typeFactory.createSqlType(SqlTypeName.FLOAT));
                    break;
                case "String":
                    types.add(typeFactory.createSqlType(SqlTypeName.VARCHAR));
                    break;
                default:
                    logger.warn("unrecognized type {} for field {}, varchar is used instead", type, field);
                    types.add(typeFactory.createSqlType(SqlTypeName.VARCHAR));
            }
        }
        return typeFactory.createStructType(Pair.zip(names, types));
    }
}
