package com.cb_labs.cb_flow_connect.web.dto.response;

import java.util.UUID;

public record TokenResponse(
    UUID tokenNetworkUuid,
    String symbol,
    String network,
    String address,
    String imageUrl
) { }
