package com.fjsh.multi.source.calcite.function.exception;

public abstract class FeatureException extends RuntimeException {
    public FeatureException(){

    }
    public FeatureException(String message){
        super(message);
    }
    public FeatureException(String message,Throwable throwable){
        super(message,throwable);
    }
    public FeatureException(Throwable throwable){
        super(throwable);
    }
    public FeatureException(String message,Throwable throwable,boolean enableSupression,boolean writableStackTrace){
        super(message,throwable,enableSupression,writableStackTrace);
    }
}
