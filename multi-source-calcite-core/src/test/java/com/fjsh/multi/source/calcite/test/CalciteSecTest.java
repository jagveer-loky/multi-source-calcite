package com.fjsh.multi.source.calcite.test;

import com.alibaba.fastjson.JSONObject;
import com.fjsh.multi.source.calcite.test.schema.JsonSchema;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.schema.SchemaPlus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.*;

@RunWith(MockitoJUnitRunner.class)
public class CalciteSecTest {

    @Before
    public void before() {

    }

    @Test
    public void mainTest() throws Exception {
        long begin = System.currentTimeMillis();
        run();
        long duration = System.currentTimeMillis() - begin;
        System.out.println("total:" + duration);
    }

    @Test
    public void run() throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.calcite.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:calcite:");
        CalciteConnection optiqConnection = (CalciteConnection) connection.unwrap(CalciteConnection.class);
        SchemaPlus rootSchema = optiqConnection.getRootSchema();

        String json = "[{\"CUST_ID\":{\"a\":1},\"PROD_ID\":220,\"USER_ID\":300,\"USER_NAME\":\"user1\"},"
                + "{\"USER_ID\":310,\"CUST_ID\":{\"a\":2},\"PROD_ID\":210,\"USER_NAME\":\"user2\"}]";

        String jsonSec = "[{\"CUST_ID\":{\"aa\":1},\"PROD_ID\":240,\"USER_ID\":300,\"USER_NAME\":\"user1\"},"
                + "{\"USER_ID\":310,\"CUST_ID\":{\"aa\":2},\"PROD_ID\":240,\"USER_NAME\":\"user2\"}]";

        Statement statement = connection.createStatement();


        ResultSet resultSet = null;
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            rootSchema.add("JSON", new JsonSchema("ABC", json));
            rootSchema.add("JSONSEC", new JsonSchema("ABCSEC", jsonSec));
            resultSet = statement.executeQuery(
                    "select json.ABC.USER_ID AS aa  from json.ABC limit 1");

//			resultSet = statement.executeQuery(
//					"select JSON.ABC.USER_ID AS aa,JSONSEC.ABCSEC.PROD_ID  from JSON.ABC,JSONSEC.ABCSEC where  JSON.ABC.USER_ID=JSONSEC.ABCSEC.USER_ID limit 1");
        }
        System.out.println("query:" + (System.currentTimeMillis() - begin));

        while (resultSet.next()) {
            JSONObject jo = new JSONObject();
            int n = resultSet.getMetaData().getColumnCount();
            for (int i = 1; i <= n; i++) {
                jo.put(resultSet.getMetaData().getColumnLabel(i), resultSet.getObject(i));
            }
            System.out.println(jo.toJSONString());
        }
        Assert.assertTrue(resultSet != null);
        resultSet.close();
        statement.close();
        connection.close();
    }
}

