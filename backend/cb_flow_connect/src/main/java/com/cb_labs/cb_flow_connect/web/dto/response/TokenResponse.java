package com.cb_labs.cb_flow_connect.web.dto.response;

import java.util.UUID;

public record TokenResponse(
    UUID uuid,
    String name,
    String network,
    String address
) { }
