package com.cb_labs.cb_flow_connect.web.dto.response;

import com.cb_labs.cb_flow_connect.persistance.entities.enums.RampType;

public record TransactionResponse(
    RampType type,
    String createdAt,
    String status,
    String cryptoAmount,
    String fiatAmount
) { }
