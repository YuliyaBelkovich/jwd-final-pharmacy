package com.epam.jwd.domain;

import com.epam.jwd.exception.UnknownEntityException;

import java.util.Arrays;

public enum RecipeRequestStatus {
    PENDING("PENDING"),
    REJECTED("REJECTED"),
    CONFIRMED("CONFIRMED");

    private final String dbName;

    RecipeRequestStatus(String dbName) {
        this.dbName = dbName;
    }

    public String getDbName() {
        return dbName;
    }

    public static RecipeRequestStatus resolveStatusByDBName(String baseName) throws UnknownEntityException {
        if (baseName == null) {
            throw new UnknownEntityException();
        }
        return Arrays.stream(values()).filter(status -> status.getDbName().equals(baseName)).findAny().get();
    }
}
