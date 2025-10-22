package com.cb_labs.cb_flow_connect.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record UserBankInformationRequest(
    @NotNull
    UUID userUuid,

    @Size(min = 18, max = 18)
    String clabe,

    @NotBlank
    String bankName,

    @NotBlank
    String country
) { }
