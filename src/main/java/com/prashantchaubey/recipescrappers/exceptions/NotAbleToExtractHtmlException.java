package com.prashantchaubey.recipescrappers.exceptions;

public class NotAbleToExtractHtmlException extends RuntimeException {
    public NotAbleToExtractHtmlException(Throwable cause) {
        super(cause);
    }

    public NotAbleToExtractHtmlException(String message) {
        super(message);
    }
}
