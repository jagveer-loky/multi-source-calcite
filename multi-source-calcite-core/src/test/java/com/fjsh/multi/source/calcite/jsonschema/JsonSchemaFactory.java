package com.fjsh.multi.source.calcite.jsonschema;

import org.apache.calcite.model.ModelHandler;
import org.apache.calcite.schema.Schema;
import org.apache.calcite.schema.SchemaFactory;
import org.apache.calcite.schema.SchemaPlus;

import java.io.File;
import java.util.Map;


public class JsonSchemaFactory implements SchemaFactory {

    public static final JsonSchemaFactory INSTANCE = new JsonSchemaFactory();

    @Override
    public Schema create(SchemaPlus parentSchema, String name, Map<String, Object> operand) {
        final String directory = (String) operand.get("directory");
        final File base = (File) operand.get(ModelHandler.ExtraOperand.BASE_DIRECTORY.camelName);
        File directoryFile = new File(directory);
        if (base != null && !directoryFile.isAbsolute()){
            directoryFile = new File(base, directory);
        }
        return new JsonSchema(directoryFile);
    }
}
