package com.fjsh.multi.source.calcite.jsonschema;

import org.apache.calcite.model.ModelHandler;
import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.rel.type.RelDataTypeImpl;
import org.apache.calcite.rel.type.RelProtoDataType;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.schema.TableFactory;
import org.apache.calcite.util.Source;
import org.apache.calcite.util.Sources;

import java.io.File;
import java.util.Map;


public class JsonTableFactory implements TableFactory<JsonTable> {

    public JsonTableFactory(){};

    @Override
    public JsonTable create(SchemaPlus schema, String name, Map<String, Object> operand, RelDataType rowType) {
        String tableName = (String) operand.get("table");
        File base = (File) operand.get(ModelHandler.ExtraOperand.BASE_DIRECTORY.name());
        Source source = Sources.file(base, tableName);
        RelProtoDataType protoRowType = rowType != null ? RelDataTypeImpl.proto(rowType) : null;
        return new JsonTable(source, protoRowType);
    }
}
