package com.epam.jwd.exception;

public class EntityNotFoundException extends Exception{

    public EntityNotFoundException() {
        super();
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
