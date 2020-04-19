package com.fjsh.multi.source.calcite.function;

import java.util.Map;

public class ExpressExecContext {
    private final Map<String,Object> inputParams;
    private RowRecord inputRowRecord;

    public ExpressExecContext(Map<String, Object> inputParams) {
        this.inputParams = inputParams;
    }

    public Map<String, Object> getInputParams() {
        return inputParams;
    }

    public RowRecord getInputRowRecord() {
        return inputRowRecord;
    }

    public void setInputRowRecord(RowRecord inputRowRecord) {
        this.inputRowRecord = inputRowRecord;
    }
}
