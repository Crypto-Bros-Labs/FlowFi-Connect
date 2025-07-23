package com.cb_labs.cb_flow_connect.web.dto.response;

import com.cb_labs.cb_flow_connect.persistance.entities.enums.OffRampDetails;

public record OffRampResponse(
    OffRampDetails details,
    String kycUrl,
    String destinationWalletAddress,
    String cryptoAmount,
    String fiatAmount
) { }
