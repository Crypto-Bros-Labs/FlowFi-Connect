package com.cb_labs.cb_flow_connect.web.dto.response;

public record QuotingResponse(
    Double fiatAmount,
    Double cryptoAmount
) { }
