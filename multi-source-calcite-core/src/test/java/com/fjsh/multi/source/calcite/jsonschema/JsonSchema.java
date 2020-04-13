package com.fjsh.multi.source.calcite.jsonschema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import org.apache.calcite.schema.Table;
import org.apache.calcite.schema.impl.AbstractSchema;
import org.codehaus.commons.nullanalysis.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Schema of table in JsonDB.
 **/
public class JsonSchema extends AbstractSchema {
    private static final String SCHEMA_FILE_NAME = ".schema";
    private static final Logger logger = LoggerFactory.getLogger(JsonSchema.class);
    private final File directoryFile;
    private Map<String, Table> tableMap;

    public JsonSchema(File directoryFile){
        super();
        this.directoryFile = directoryFile;
    }

    @Override
    protected Map<String, Table> getTableMap(){
        if(tableMap == null){
            try {
                createTableMap();
            } catch (IOException e) {
                logger.error("failed to get table map", e);
            }
        }
        return tableMap;
    }

    /** Reads schema from the specific schema file .schema.json in the table directory. */
    private void createTableMap() throws IOException {
        String schemaPath = directoryFile.getAbsolutePath() + File.separator + SCHEMA_FILE_NAME;
        File schemaFile = new File(schemaPath);
        if (!schemaFile.exists() && schemaFile.isDirectory()){
            throw new IOException(String.format("failed to read table schema file %s", schemaFile.getAbsolutePath()));
        }
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readValue(schemaFile, JsonNode.class);
        if(root.getNodeType() != JsonNodeType.OBJECT){
            throw new IllegalArgumentException(".schema must be JSON object");
        }
        ArrayNode fields = (ArrayNode) root.get("fields");
    }

}
