package com.microservice.authentication.exception;

public class NotAbleToDeleteException extends RuntimeException{
    public NotAbleToDeleteException() {
    }

    public NotAbleToDeleteException(String message) {
        super(message);
    }

    public NotAbleToDeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAbleToDeleteException(Throwable cause) {
        super(cause);
    }

    public NotAbleToDeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
