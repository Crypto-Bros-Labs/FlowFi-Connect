package com.cb_labs.cb_flow_connect.web.dto.models.capa.request;

import java.util.UUID;

public record CapaUserRequest(
    String type,
    String email,
    UUID externalUserId
) { }
