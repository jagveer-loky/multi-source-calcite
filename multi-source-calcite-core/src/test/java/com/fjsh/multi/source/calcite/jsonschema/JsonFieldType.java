package com.fjsh.multi.source.calcite.jsonschema;

import org.apache.calcite.adapter.java.JavaTypeFactory;
import org.apache.calcite.linq4j.tree.Primitive;
import org.apache.calcite.rel.type.RelDataType;

import java.util.HashMap;
import java.util.Map;

/**
 *  Map SQL data types to corresponding Java type.
 */

enum JsonFieldType {
    STRING(String.class, "string"),
    BOOLEAN(Primitive.BOOLEAN),
    INT(Primitive.INT),
    LONG(Primitive.LONG),
    DOUBLE(Primitive.DOUBLE),
    FLOAT(Primitive.FLOAT),
    DATE(java.sql.Date.class, "date"),
    TIME(java.sql.Time.class, "time"),
    TIMESTAMP(java.sql.Timestamp.class, "timestamp");

    private final Class clazz;
    private final String simpleName;

    private static final Map<String, JsonFieldType> MAP = new HashMap<>(16);

    static {
        for (JsonFieldType value: values()){
            MAP.put(value.simpleName, value);
        }
    }

    JsonFieldType(Primitive primitive){
        this(primitive.boxClass, primitive.primitiveClass.getSimpleName());
    }

    JsonFieldType(Class clazz, String simpleName){
        this.clazz = clazz;
        this.simpleName = simpleName;
    }

    public RelDataType toType(JavaTypeFactory typeFactory){
        RelDataType javaType = typeFactory.createJavaType(clazz);
        RelDataType sqlType = typeFactory.createSqlType(javaType.getSqlTypeName());
        return typeFactory.createTypeWithNullability(sqlType, true);
    }

    public static JsonFieldType of(String typeString) { return MAP.get(typeString); }

}
