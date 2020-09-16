package com.prashantchaubey.recipescrappers;

public class NotAbleToExtractHtmlException extends RuntimeException {
    public NotAbleToExtractHtmlException(Throwable cause) {
        super(cause);
    }

    public NotAbleToExtractHtmlException(String message) {
        super(message);
    }
}
