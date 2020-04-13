package com.fjsh.multi.source.calcite.jsonschema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.calcite.util.Source;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Reader for reading json data files, which will read the entire file in to memory to parse, so is not suitable for
 * reading big files.

 **/
public class BulkJsonReader {
    private ObjectMapper objectMapper;
    private File file;

    public BulkJsonReader(Source source){
        objectMapper = new ObjectMapper();
        this.file = source.file();
    }

    public List<ObjectNode> read() throws IOException{
        List<ObjectNode> nodes = new ArrayList<>(256);
        JsonNode root = objectMapper.readValue(file, JsonNode.class);
        if(root.isArray()){
            ArrayNode arrayNode = (ArrayNode) root;
            Iterator<JsonNode> iterator = arrayNode.iterator();
            while (iterator.hasNext()){
                JsonNode node = iterator.next();
                if(!(node.getNodeType() == JsonNodeType.OBJECT)){
                    throw new IllegalArgumentException("row object must be of object type");
                }
                nodes.add((ObjectNode) node);
            }
        } else if(root.isObject()){
            nodes.add((ObjectNode) root);
        }
        return nodes;
    }
}
