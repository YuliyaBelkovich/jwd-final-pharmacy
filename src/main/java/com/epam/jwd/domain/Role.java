package com.epam.jwd.domain;

import java.util.Arrays;

/**
 * Role of {@link User}
 * Guest is used for indicating the allowed role for the command classes, does not stored in the database
 */
public enum Role {
    DOCTOR ("DOCTOR"),
    PATIENT("PATIENT"),
    PHARMACIST("PHARMACIST"),
    GUEST("GUEST");

    private final String baseName;

    Role(String baseName) {
        this.baseName = baseName;
    }

    public String getBaseName() {
        return baseName;
    }
    /**
     *
     * @param baseName - string representation of {@link Role} object
     * @return null if baseName is null
     * @return {@link Role} if finds appropriate object
     */
    public static Role resolveRoleByDBName(String baseName){
        if(baseName==null){
            return null;
        }
        return Arrays.stream(values()).filter(role -> role.getBaseName().equals(baseName)).findAny().get();
    }
}
