package com.fjsh.multi.source.calcite.function;

public class EvalResult {
    private final boolean success;
    private final Object value;
    private final String errorMsg;

    public EvalResult(boolean success, Object value, String errorMsg) {
        this.success = success;
        this.value = value;
        this.errorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public Object getValue() {
        return value;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
    public static EvalResult successResult(Object value){
        return new EvalResult(true,value,null);
    }
}
