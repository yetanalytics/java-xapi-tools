package com.yetanalytics.xapi.exception;

public class StatementClientException extends RuntimeException {
    
    public StatementClientException() {
        super();
    }

    public StatementClientException(String message) {
        super(message);
    }

    public StatementClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public StatementClientException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return "StatementClientException: " + super.getMessage();
    }
}
