package com.epam.jwd.exception;

public class ValidationException  extends Exception{
    public ValidationException() {
        super();
    }

    public ValidationException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
