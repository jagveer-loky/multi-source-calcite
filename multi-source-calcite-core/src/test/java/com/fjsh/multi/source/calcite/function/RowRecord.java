package com.fjsh.multi.source.calcite.function;

import com.google.common.base.Preconditions;

import java.util.List;

public class RowRecord {
    private final RowType rowType;
    private final List<Object> record;

    public RowRecord(RowType rowType, List<Object> record) {
        this.rowType = rowType;
        this.record = record;
    }

    public RowType getRowType() {
        return rowType;
    }

    public List<Object> getRecord() {
        return record;
    }
    public Object getFieldValue(String fieldName){
        int fieldIndex=rowType.getFieldIndex(fieldName);
        Preconditions.checkState(fieldIndex>=0);
        return record.get(fieldIndex);
    }

    @Override
    public String toString() {
        return "RowRecord{" +
                "rowType=" + rowType +
                ", record=" + record +
                '}';
    }
}
