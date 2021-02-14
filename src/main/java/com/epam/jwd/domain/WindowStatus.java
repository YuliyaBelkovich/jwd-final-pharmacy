package com.epam.jwd.domain;

import java.util.Arrays;

/**
 * Status for {@link AppointmentWindow}
 */
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
    /**
     *
     * @param baseName - string representation of {@link WindowStatus} object
     * @return null if baseName is null
     * @return {@link WindowStatus} if finds appropriate object
     */
    public static WindowStatus resolveStatusByDBName(String baseName){
        if (baseName == null) {
            return null;
        }
        return Arrays.stream(values()).filter(status -> status.getDbName().equals(baseName)).findAny().get();
    }
}
