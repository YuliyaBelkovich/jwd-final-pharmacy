package com.epam.jwd.domain;

import java.util.Arrays;

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

    public static Role resolveRoleByDBName(String baseName){
        if(baseName==null){
            return null;
        }
        return Arrays.stream(values()).filter(role -> role.getBaseName().equals(baseName)).findAny().get();
    }
}
