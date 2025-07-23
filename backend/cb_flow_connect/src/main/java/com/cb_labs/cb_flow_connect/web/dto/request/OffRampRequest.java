package com.cb_labs.cb_flow_connect.web.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record OffRampRequest(
    @NotNull
    UUID userUuid,

    @NotNull
    UUID providerUuid,

    @NotNull
    UUID tokenNetworkUuid,

    @NotNull
    UUID fiatCurrencyUuid,

    @NotNull
    UUID userBankInformationUuid,

    @NotNull
    Double amount
) { }
