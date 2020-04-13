package com.fjsh.multi.source.calcite.jsonschema;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.util.Source;
import org.apache.calcite.util.Sources;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class BufferedJsonReaderSecTest {

    @Test
    public void readJsonFile() throws Exception{
        Source source = Sources.file(new File("src/test/resource/"),
                "data.json");

        Class.forName("org.apache.calcite.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:calcite:");
        CalciteConnection optiqConnection = (CalciteConnection) connection.unwrap(CalciteConnection.class);
        SchemaPlus rootSchema = optiqConnection.getRootSchema();
        rootSchema.add("JSON", new JsonSchema(new File("src/test/resource/data.json")));
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "select json.name AS aa  from json limit 1");
        while (resultSet.next()) {
            JSONObject jo = new JSONObject();
            int n = resultSet.getMetaData().getColumnCount();
            for (int i = 1; i <= n; i++) {
                jo.put(resultSet.getMetaData().getColumnLabel(i), resultSet.getObject(i));
            }
            System.out.println(jo.toJSONString());
        }




        BulkJsonReader bulkJsonReader = new BulkJsonReader(source);
        try {
            List<ObjectNode> target = new ArrayList<>();
            ObjectNode objectNode = new ObjectMapper().createObjectNode();
            objectNode.put("name", "whitewood");
            objectNode.put("age", "24");
            target.add(objectNode);
            Assert.assertEquals(target, bulkJsonReader.read());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
