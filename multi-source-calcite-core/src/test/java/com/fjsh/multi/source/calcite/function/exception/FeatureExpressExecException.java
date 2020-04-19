package com.fjsh.multi.source.calcite.function.exception;

public class FeatureExpressExecException extends FeatureException {
    public FeatureExpressExecException(){

    }
    public FeatureExpressExecException(String message){
        super(message);
    }
    public FeatureExpressExecException(String message,Throwable throwable){
        super(message,throwable);
    }
    public FeatureExpressExecException(Throwable throwable){
        super(throwable);
    }
    public FeatureExpressExecException(String message,Throwable throwable,boolean enableSupression,boolean writableStackTrace){
        super(message,throwable,enableSupression,writableStackTrace);
    }
}
