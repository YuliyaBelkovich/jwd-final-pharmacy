package com.epam.jwd.exception;

public class AuthenticationException extends Exception{
    public AuthenticationException() {
    }

    public AuthenticationException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
