package com.epam.jwd.domain;

import com.epam.jwd.exception.UnknownEntityException;

import java.util.Arrays;

public enum WindowStatus {
    BUSY("BUSY"),
    FREE("FREE");

    private final String dbName;


    WindowStatus(String dbName) {
        this.dbName = dbName;
    }

    public String getDbName() {
        return dbName;
    }

    public static WindowStatus resolveStatusByDBName(String baseName){
        if (baseName == null) {
            return null;
        }
        return Arrays.stream(values()).filter(status -> status.getDbName().equals(baseName)).findAny().get();
    }
}
