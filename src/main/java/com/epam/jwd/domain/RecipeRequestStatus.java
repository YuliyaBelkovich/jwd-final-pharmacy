package com.epam.jwd.domain;

import java.util.Arrays;

/**
 * Status for {@link RecipeProlongationRequest}
 */
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

    public static RecipeRequestStatus resolveStatusByDBName(String baseName) {
        if (baseName == null) {
            return null;
        }
        return Arrays.stream(values()).filter(status -> status.getDbName().equals(baseName)).findAny().get();
    }
}
