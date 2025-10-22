package com.cb_labs.cb_flow_connect.web.dto.models.capa.response;

import java.util.UUID;

public record CapaOffRampResponse(
    Boolean success,
    OffRampResponse data
) {
    public record OffRampResponse(
        UUID id,
        UUID userId,
        String status,
        Double cryptoAmount,
        Double fiatAmount,
        Double exchangeRate,
        String tokenSymbol,
        String blockchainToToken,
        String fiatCurrency,
        Float premiumSpread,
        String createdAt,
        String completedAt,
        String destinationWalletAddress,
        BankAccount bankAccount
    ) {
        public record BankAccount(
            String country,
            String accountIdentifier,
            String accountType,
            String isVerified,
            String documentIdentifier,
            String documentType
        ) {}
    }
}
