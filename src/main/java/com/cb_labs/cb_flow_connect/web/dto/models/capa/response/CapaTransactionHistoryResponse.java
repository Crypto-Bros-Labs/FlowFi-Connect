package com.cb_labs.cb_flow_connect.web.dto.models.capa.response;

import java.util.List;

public record CapaTransactionHistoryResponse(
    Boolean success,
    TransactionHistoryResponse data
) {
    public record TransactionHistoryResponse(
        Long count,
        List<TransactionResponse> data
    ) {
        public record TransactionResponse(
            String id,
            String userId,
            String status,
            String type,
            String tokenSymbol,
            String blockchainSymbol,
            String fiatCurrency,
            Double fiatAmount,
            Double cryptoAmount,
            Double exchangeRate,
            Double premiumSpread,
            String createdAt,
            String completedAt,
            String destinationWalletAddress,
            BankAccount bankAccount,
            Invoice invoice,
            String cancellationReason
        ) {
            public record BankAccount(
                String country,
                String accountIdentifier,
                String bankName,
                String accountType,
                Boolean isVerified,
                String documentIdentifier
            ) {}
            public record Invoice(
                String transactionHash,
                String paymentUrl
            ) {}
        }
    }
}
