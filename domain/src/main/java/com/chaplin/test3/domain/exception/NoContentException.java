package com.chaplin.test3.domain.exception;

public class NoContentException extends Exception {
    public NoContentException() {
        super("No content in server response");
    }
}
