package com.cb_labs.cb_flow_connect.web.dto.response;

public record JwtResponse(
        String token,
        String refreshToken
) { }
