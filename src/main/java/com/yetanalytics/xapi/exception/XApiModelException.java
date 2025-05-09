package com.yetanalytics.xapi.exception;

public class XApiModelException extends RuntimeException {
    
    public XApiModelException() {
        super();
    }

    public XApiModelException(String message) {
        super(message);
    }

    public XApiModelException(String message, Throwable cause) {
        super(message, cause);
    }

    public XApiModelException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return "XApiModelException: " + super.getMessage();
    }
}
