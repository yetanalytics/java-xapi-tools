package com.yetanalytics.xapi.exception;

public class StatementClientException extends RuntimeException {

    private Integer statusCode;
    
    public StatementClientException() {
        super();
    }

    public StatementClientException(String message) {
        super(message);
    }

    public StatementClientException(String message, Integer status) {
        super(message);
        this.statusCode = status;
    }

    public StatementClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public StatementClientException(String message, Integer status, Throwable cause) {
        super(message, cause);
        this.statusCode = status;
    }

    public StatementClientException(Throwable cause) {
        super(cause);
    }

    public StatementClientException(Integer status, Throwable cause) {
        super(cause);
        this.statusCode = status;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    @Override
    public String getMessage() {
        return "StatementClientException: " + super.getMessage();
    }
}
