package com.epam.jwd.domain;

import java.util.Arrays;

public enum AppointmentStatus {
    PLANNED("PLANNED"),
    CANCELLED("CANCELLED"),
    PLACED("PLACED");

    AppointmentStatus(String dbName) {
        this.dbName = dbName;
    }

    private final String dbName;

    public String getDbName() {
        return dbName;
    }

    /**
     *
     * @param baseName - string representation of {@link AppointmentStatus} object
     * @return null if baseName is null
     * @return {@link AppointmentStatus} if finds appropriate object
     */
    public static AppointmentStatus resolveStatusByDBName(String baseName) {
        if (baseName == null) {
           return null;
        }
        return Arrays.stream(values()).filter(status -> status.getDbName().equals(baseName)).findAny().get();
    }
}
