package com.fjsh.multi.source.calcite.sqlparser.validator;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 */
public class ConnectionFactoryTest {

    public void createSchemaFactoryConnection() throws SQLException {
        Properties info = new Properties();
        info.put("lex", "mysql");
        String model = "multi-source-calcite-core/src/test/resource/model.json";
        info.put("model", model);
        DriverManager.getConnection("jdbc:calcite:", info);
    }

    public void createTableFactoryConnection() throws SQLException {
        Properties info = new Properties();
        info.put("lex", "mysql");
        String model = "multi-source-calcite-core/src/test/resource/model.yaml";
        info.put("model", model);
        DriverManager.getConnection("jdbc:calcite:", info);
    }

    public static void main(String[] args) throws SQLException {
        new ConnectionFactoryTest().createTableFactoryConnection();

    }
}
