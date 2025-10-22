package com.cb_labs.cb_flow_connect.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UserRequest(
    @NotNull
    UUID userUuid,

    @NotBlank(message = "The phone is required")
    String phone,

    @NotBlank(message = "The full name is required")
    String fullName,

    String image
) { }
