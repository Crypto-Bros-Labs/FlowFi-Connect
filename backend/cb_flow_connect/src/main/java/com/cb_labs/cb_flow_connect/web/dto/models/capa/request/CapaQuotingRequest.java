package com.cb_labs.cb_flow_connect.web.dto.models.capa.request;

public record CapaQuotingRequest(
    String tokenSymbol,
    String transactionType,
    String blockchainSymbol,
    String fiatCurrency,
    Long cryptoAmount,
    Double fiatAmount,
    Double premiumSpread
) {

    public String toQueryParams() {
        StringBuilder sb = new StringBuilder();
        try {
            if (tokenSymbol != null) {
                sb.append("tokenSymbol=")
                  .append(java.net.URLEncoder.encode(tokenSymbol, "UTF-8"))
                  .append("&");
            }
            if (transactionType != null) {
                sb.append("transactionType=")
                  .append(java.net.URLEncoder.encode(transactionType, "UTF-8"))
                  .append("&");
            }
            if (blockchainSymbol != null) {
                sb.append("blockchainSymbol=")
                  .append(java.net.URLEncoder.encode(blockchainSymbol, "UTF-8"))
                  .append("&");
            }
            if (fiatCurrency != null) {
                sb.append("fiatCurrency=")
                  .append(java.net.URLEncoder.encode(fiatCurrency, "UTF-8"))
                  .append("&");
            }
            if (cryptoAmount != null) {
                sb.append("cryptoAmount=").append(cryptoAmount).append("&");
            }
            if (fiatAmount != null) {
                sb.append("fiatAmount=").append(fiatAmount).append("&");
            }
            if (premiumSpread != null) {
                sb.append("premiumSpread=").append(premiumSpread).append("&");
            }
            // Only remove trailing '&' if it is present for clarity
            boolean hasTrailingAmp = sb.length() > 0 && sb.charAt(sb.length() - 1) == '&';
            if (hasTrailingAmp) {
                sb.setLength(sb.length() - 1);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error encoding query parameters", e);
        }
        return sb.toString();
    }

}
