package com.cb_labs.cb_flow_connect.persistance.entities.enums;

import lombok.Getter;

@Getter
public enum RampType {
    ON_RAMP("ON_RAMP"),
    OFF_RAMP("OFF_RAMP");

    private String value;

    RampType(String value) {
        this.value = value;
    }
}
