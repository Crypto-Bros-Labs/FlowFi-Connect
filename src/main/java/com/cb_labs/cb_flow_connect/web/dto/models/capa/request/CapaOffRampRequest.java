package com.cb_labs.cb_flow_connect.web.dto.models.capa.request;

public record CapaOffRampRequest(
        CapaUserBankInformationObject userBankInformation,
        String userId,
        Long cryptoAmount, // Changed from String to Long
        String fiatCurrency,
        String blockchainSymbol,
        String tokenSymbol,
        Float premiumSpread
) {
    public record CapaUserBankInformationObject(
            String accountIdentifier,
            String country
    ) {}
}
