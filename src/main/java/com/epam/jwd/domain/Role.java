package com.epam.jwd.domain;

import com.epam.jwd.exception.UnknownEntityException;

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

    public static Role resolveRoleByDBName(String baseName) throws UnknownEntityException {
        if(baseName==null){
            throw new UnknownEntityException();
        }
        return Arrays.stream(values()).filter(role -> role.getBaseName().equals(baseName)).findAny().get();
    }
}
