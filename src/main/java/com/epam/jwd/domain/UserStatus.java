package com.epam.jwd.domain;

import java.util.Arrays;

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

    public static UserStatus resolveStatusByDBName(String baseName)  {
        if (baseName == null) {
            return null;
        }
        return Arrays.stream(values()).filter(status -> status.getDbName().equals(baseName)).findAny().get();
    }
}
