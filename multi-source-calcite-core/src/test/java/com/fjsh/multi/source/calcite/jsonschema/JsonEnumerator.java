package com.fjsh.multi.source.calcite.jsonschema;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.calcite.linq4j.Enumerator;
import org.apache.calcite.util.Source;
import org.apache.commons.lang.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

/**
 * Iterates over json files and produces records. Not thread-safe.

 **/

public class JsonEnumerator implements Enumerator {
    private final BulkJsonReader reader;
    private final String[] filterValues;
    private final List<ObjectNode> objects;
    private Iterator<ObjectNode> iterator;
    private ObjectNode current;

    private static final FastDateFormat TIME_FORMAT_DATE;
    private static final FastDateFormat TIME_FORMAT_TIME;
    private static final FastDateFormat TIME_FORMAT_TIMESTAMP;

    private static Logger logger = LoggerFactory.getLogger(JsonEnumerator.class);

    static {
        final TimeZone gmt = TimeZone.getTimeZone("GMT+8");
        TIME_FORMAT_DATE = FastDateFormat.getInstance("yyyy-MM-dd", gmt);
        TIME_FORMAT_TIME = FastDateFormat.getInstance("HH:mm:ss", gmt);
        TIME_FORMAT_TIMESTAMP = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss", gmt);
    }

    JsonEnumerator(Source source, String[] filterValues) {
        this.filterValues = filterValues;
        //TODO: implements stream parser
        this.reader = new BulkJsonReader(source);
        try {
            this.objects = reader.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.iterator = objects.iterator();
        if(iterator.hasNext()) {
            this.current = iterator.next();
        } else{
            this.current = null;
        }
    }


    @Override
    public ObjectNode current() {
        return current;
    }

    @Override
    public boolean moveNext() {
        if(iterator.hasNext()) {
            current = iterator.next();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void reset() {
        iterator = this.objects.iterator();
    }

    @Override
    public void close() {
    }

}
