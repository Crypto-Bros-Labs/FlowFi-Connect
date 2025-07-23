package com.cb_labs.cb_flow_connect.web.dto.models.capa.response;


public record CapaQuotingResponse(
    Boolean success,
    QuotingData data
) {

    public record QuotingData(
        String transactionType,
        Double fiatAmount,
        String fiatCurrency,
        String blockchainSymbol,
        String tokenSymbol,
        Long cryptoAmount,
        Double rate,
        String flow
    ) {}

}
