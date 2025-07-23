package com.cb_labs.cb_flow_connect.persistance.entities.enums;

import lombok.Getter;

@Getter
public enum CapaSupportedCountries {
    MX("MX"),
    DO("DO");

    private final String value;

    CapaSupportedCountries(String value) {
        this.value = value;
    }
}
