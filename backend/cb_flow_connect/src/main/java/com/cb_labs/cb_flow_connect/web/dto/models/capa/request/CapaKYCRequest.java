package com.cb_labs.cb_flow_connect.web.dto.models.capa.request;

public record CapaKYCRequest(
    String country,
    String partnerRedirectUri
) { }
