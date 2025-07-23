package com.cb_labs.cb_flow_connect.persistance.entities.enums;

import lombok.Getter;

@Getter
public enum CapaAccountType {
    INDIVIDUAL("INDIVIDUAL"),
    BUSINESS("BUSINESS");

    private final String value;

    CapaAccountType(String value) {
        this.value = value;
    }
}
