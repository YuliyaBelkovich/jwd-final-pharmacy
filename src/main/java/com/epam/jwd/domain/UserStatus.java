package com.epam.jwd.domain;

import java.util.Arrays;

/**
 * Status for {@link User}
 */
public enum UserStatus {

    ACTIVE("ACTIVE"),
    BANNED("BANNED"),
    PENDING("PENDING");

    private final String dbName;

    UserStatus(String dbName) {
        this.dbName = dbName;
    }

    public String getDbName() {
        return dbName;
    }
    /**
     *
     * @param baseName - string representation of {@link UserStatus} object
     * @return null if baseName is null
     * @return {@link UserStatus} if finds appropriate object
     */
    public static UserStatus resolveStatusByDBName(String baseName)  {
        if (baseName == null) {
            return null;
        }
        return Arrays.stream(values()).filter(status -> status.getDbName().equals(baseName)).findAny().get();
    }
}
