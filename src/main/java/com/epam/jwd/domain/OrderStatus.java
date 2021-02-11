package com.epam.jwd.domain;

import java.util.Arrays;

public enum OrderStatus {
    PAID("PAID"),
    NOT_PAID("NOT PAID");

    private final String dbName;

    OrderStatus(String dbName) {
        this.dbName = dbName;
    }

    public String getDbName() {
        return dbName;
    }

    public static OrderStatus resolveStatusByDBName(String baseName) {
        if (baseName == null) {
            return null;
        }
        return Arrays.stream(values()).filter(status -> status.getDbName().equals(baseName)).findAny().get();
    }
}
