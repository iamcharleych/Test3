package com.chaplin.test3.data.exception;

public class PollValidationException extends Exception {
    public PollValidationException() {
        super("Poll validation failed");
    }
}
