package com.fjsh.multi.source.calcite.sqlparser.validator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.schema.Table;
import org.apache.calcite.schema.TableFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * table工厂
 */
public class TutorialTableFactory implements TableFactory {

    /**
     * yaml解析器
     */
    private static final ObjectMapper YAML_MAPPER = new YAMLMapper();

    /**
     * 创建table
     * @param schema
     * @param name
     * @param operand
     * @param rowType
     * @return
     */
    @Override
    public Table create(SchemaPlus schema, String name, Map operand, RelDataType rowType) {
        try {
            String ddl = (String) operand.get("ddl");
            return YAML_MAPPER.readValue(new File(ddl), TutorialTable.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
