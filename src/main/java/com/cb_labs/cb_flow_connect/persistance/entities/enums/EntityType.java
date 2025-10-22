package com.cb_labs.cb_flow_connect.persistance.entities.enums;

import lombok.Getter;

@Getter
public enum EntityType {
    INDIVIDUAL("INDIVIDUAL"),
    BUSINESS("BUSINESS");

    private final String value;

    EntityType(String value) {
        this.value = value;
    }
}
