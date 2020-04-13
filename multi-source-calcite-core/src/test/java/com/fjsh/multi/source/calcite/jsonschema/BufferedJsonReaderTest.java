package com.fjsh.multi.source.calcite.jsonschema;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.calcite.util.Source;
import org.apache.calcite.util.Sources;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class BufferedJsonReaderTest {

    @Test
    public void readJsonFile(){
        Source source = Sources.file(new File("src/test/resource/"),
                "data.json");
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
