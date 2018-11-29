package com.chaplin.test3.domain.exception;

public class PollValidationException extends Exception {
    public PollValidationException() {
        super("Poll validation failed");
    }
}
