package com.cb_labs.cb_flow_connect.web.dto.request;

import java.util.UUID;

public record OnRampRequest(
    UUID userUuid,
    UUID providerUuid,
    UUID tokenUuid,
    Double amount
) { }
