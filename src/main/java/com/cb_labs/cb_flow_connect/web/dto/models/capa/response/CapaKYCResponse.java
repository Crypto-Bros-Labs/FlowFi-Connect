package com.cb_labs.cb_flow_connect.web.dto.models.capa.response;

public record CapaKYCResponse(
    Boolean success,
    KYCResponse data
) {
    public record KYCResponse(
        String kycLink
    ) {}
}
