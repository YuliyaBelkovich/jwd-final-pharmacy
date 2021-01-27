package com.epam.jwd.exception;

public class DAOException extends Exception{

    public DAOException() {
    }

    public DAOException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}

