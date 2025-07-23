package com.cb_labs.cb_flow_connect.web.dto.response;

import java.util.UUID;

public record UserBankInformationResponse(
    UUID userBankInformationUuid,
    String clabe,
    String bankName,
    String country
) { }
