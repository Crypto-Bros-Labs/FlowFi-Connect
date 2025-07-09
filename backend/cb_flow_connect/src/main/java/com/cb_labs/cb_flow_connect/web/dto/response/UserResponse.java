package com.cb_labs.cb_flow_connect.web.dto.response;

import java.util.UUID;

public record UserResponse(
    UUID userUuid,
    String phone,
    String fullName,
    String email
) { }
