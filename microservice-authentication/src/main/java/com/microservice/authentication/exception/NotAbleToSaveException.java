package com.microservice.authentication.exception;


public class NotAbleToSaveException extends RuntimeException{
    public NotAbleToSaveException() {
    }

    public NotAbleToSaveException(String message) {
        super(message);
    }

    public NotAbleToSaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAbleToSaveException(Throwable cause) {
        super(cause);
    }

    public NotAbleToSaveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

