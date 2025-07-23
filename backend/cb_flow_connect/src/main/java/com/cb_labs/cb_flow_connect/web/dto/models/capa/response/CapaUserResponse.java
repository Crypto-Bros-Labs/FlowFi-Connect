package com.cb_labs.cb_flow_connect.web.dto.models.capa.response;

public record CapaUserResponse(
    Boolean success,
    UserResponse data
) {
    public record UserResponse(
        String userId
    ) { }
}
