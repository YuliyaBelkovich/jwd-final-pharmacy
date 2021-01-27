package com.epam.jwd.domain;

import com.epam.jwd.exception.UnknownEntityException;

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

    public static AppointmentStatus resolveStatusByDBName(String baseName) {
        if (baseName == null) {
           return null;
        }
        return Arrays.stream(values()).filter(status -> status.getDbName().equals(baseName)).findAny().get();
    }
}
