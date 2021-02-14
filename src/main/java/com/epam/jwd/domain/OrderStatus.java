package com.epam.jwd.domain;

import java.util.Arrays;

/**
 * Status for {@link Order}
 */
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
    /**
     *
     * @param baseName - string representation of {@link OrderStatus} object
     * @return null if baseName is null
     * @return {@link OrderStatus} if finds appropriate object
     */
    public static OrderStatus resolveStatusByDBName(String baseName) {
        if (baseName == null) {
            return null;
        }
        return Arrays.stream(values()).filter(status -> status.getDbName().equals(baseName)).findAny().get();
    }
}
