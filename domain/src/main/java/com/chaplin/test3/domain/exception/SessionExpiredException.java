package com.chaplin.test3.domain.exception;

public class SessionExpiredException extends Exception {
    public SessionExpiredException() {
        super("Session has expired.");
    }
}
