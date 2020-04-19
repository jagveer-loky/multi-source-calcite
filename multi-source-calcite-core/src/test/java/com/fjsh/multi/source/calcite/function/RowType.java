package com.fjsh.multi.source.calcite.function;

import com.google.common.collect.Lists;

import java.util.List;

public class RowType {
    private final List<String> fieldNames;
    private final List<ValueTypeEnum> valueTypes;

    public RowType(List<String> fieldNames, List<ValueTypeEnum> valueTypes) {
        this.fieldNames = fieldNames;
        this.valueTypes = valueTypes;
    }

    public int size() {
        return fieldNames.size();
    }

    public List<String> getFieldNames() {
        return fieldNames;
    }

    public List<ValueTypeEnum> getValueTypes() {
        return valueTypes;
    }

    public int getFieldIndex(String fieldName) {
        int index = fieldNames.indexOf(fieldName);
        return index;
    }

    @Override
    public String toString() {
        return "RowType{" +
                "fieldNames=" + fieldNames +
                ", valueTypes=" + valueTypes +
                '}';
    }

    public static class Builder {
        private List<String> fieldNames = Lists.newArrayList();
        private List<ValueTypeEnum> valueTypes = Lists.newArrayList();

        public static Builder create() {
            return new Builder();
        }

        private Builder() {

        }

        public Builder addField(String fieldName, ValueTypeEnum valueTypeEnum) {
            fieldNames.add(fieldName);
            valueTypes.add(valueTypeEnum);
            return this;
        }

        public RowType build() {
            return new RowType(fieldNames, valueTypes);
        }
    }
}
