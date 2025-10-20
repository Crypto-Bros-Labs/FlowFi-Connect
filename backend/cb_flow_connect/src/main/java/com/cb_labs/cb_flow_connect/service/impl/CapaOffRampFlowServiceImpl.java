package com.cb_labs.cb_flow_connect.service.impl;

import com.cb_labs.cb_flow_connect.persistance.entities.CapaAccount;
import com.cb_labs.cb_flow_connect.persistance.entities.FiatCurrency;
import com.cb_labs.cb_flow_connect.persistance.entities.LiquidityProvider;
import com.cb_labs.cb_flow_connect.persistance.entities.User;
import com.cb_labs.cb_flow_connect.persistance.entities.UserBankInformation;
import com.cb_labs.cb_flow_connect.persistance.entities.enums.CapaSupportedCountries;
import com.cb_labs.cb_flow_connect.persistance.entities.enums.KYCStatus;
import com.cb_labs.cb_flow_connect.persistance.entities.enums.OffRampDetails;
import com.cb_labs.cb_flow_connect.persistance.entities.enums.RampType;
import com.cb_labs.cb_flow_connect.persistance.entities.pivots.TokenNetwork;
import com.cb_labs.cb_flow_connect.service.ICapaAccountService;
import com.cb_labs.cb_flow_connect.service.ICapaRampFlowService;
import com.cb_labs.cb_flow_connect.service.ICapaService;
import com.cb_labs.cb_flow_connect.service.IFiatCurrencyService;
import com.cb_labs.cb_flow_connect.service.IUserBankInformationService;
import com.cb_labs.cb_flow_connect.service.IUserService;
import com.cb_labs.cb_flow_connect.web.dto.models.capa.request.CapaKYCRequest;
import com.cb_labs.cb_flow_connect.web.dto.models.capa.request.CapaKYCStatusResponse;
import com.cb_labs.cb_flow_connect.web.dto.models.capa.request.CapaOffRampRequest;
import com.cb_labs.cb_flow_connect.web.dto.models.capa.request.CapaQuotingRequest;
import com.cb_labs.cb_flow_connect.web.dto.models.capa.request.CapaUserRequest;
import com.cb_labs.cb_flow_connect.web.dto.models.capa.response.CapaKYCResponse;
import com.cb_labs.cb_flow_connect.web.dto.models.capa.response.CapaOffRampResponse;
import com.cb_labs.cb_flow_connect.web.dto.models.capa.response.CapaQuotingResponse;
import com.cb_labs.cb_flow_connect.web.dto.models.capa.response.CapaUserResponse;
import com.cb_labs.cb_flow_connect.web.dto.request.OffRampRequest;
import com.cb_labs.cb_flow_connect.web.dto.response.BaseResponse;
import com.cb_labs.cb_flow_connect.web.dto.response.OffRampResponse;
import com.cb_labs.cb_flow_connect.web.dto.response.QuotingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class CapaOffRampFlowServiceImpl implements ICapaRampFlowService {

    @Autowired
    private ICapaService capaService;

    @Autowired
    private ICapaAccountService capaAccountService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserBankInformationService userBankInformationService;

    @Autowired
    private IFiatCurrencyService fiatCurrencyService;

    @Override
    public BaseResponse quoting(TokenNetwork tokenNetwork, RampType rampType, UUID fiatUuid, Long cryptoAmount, Double fiatAmount) {
        FiatCurrency fiatCurrency = fiatCurrencyService.getFiatCurrencyByUuid(fiatUuid);

        Long cryptoAmountFormatted = null;
        if (cryptoAmount != null) {
            cryptoAmountFormatted = BigDecimal.valueOf(cryptoAmount)
                    .multiply(BigDecimal.TEN.pow(tokenNetwork.getToken().getDecimals()))
                    .longValue();
        }

        CapaQuotingRequest request = new CapaQuotingRequest(
            tokenNetwork.getToken().getSymbol(),
            rampType.getValue(),
            tokenNetwork.getNetwork().getSymbol(),
            fiatCurrency.getSymbol(),
            cryptoAmountFormatted,
            fiatAmount,
            0.03
        );

        CapaQuotingResponse capaQuotingResponse = capaService.quoting(request);
        BigDecimal apiCryptoAmount = BigDecimal.valueOf(capaQuotingResponse.data().cryptoAmount());
        QuotingResponse response = new QuotingResponse(
            capaQuotingResponse.data().fiatAmount(),
            apiCryptoAmount.doubleValue(),
            apiCryptoAmount.setScale(6, RoundingMode.HALF_UP).doubleValue()
        );

        return BaseResponse.builder()
                .data(response)
                .message("Prices quoted")
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .code(200).build();
    }

    @Override
    public BaseResponse offRampFlow(User user, LiquidityProvider provider, TokenNetwork tokenNetwork, OffRampRequest request) {
        UserBankInformation userBankInformation = userBankInformationService.getById(request.userBankInformationUuid());
        Optional<CapaAccount> account = Optional.ofNullable(user.getCapaAccount());

        if (account.isEmpty()) {
            return createCapaAccount(user);
        }

        CapaAccount capaAccount = account.get();
        CapaKYCStatusResponse statusResponse = capaService.getKYCStatus(capaAccount.getUuid());

        KYCStatus validateKyc = validateKyc(statusResponse.data());

        switch (validateKyc) {
            case NOT_STARTED, INCOMPLETE -> {
                return retryKYCGeneration(user);
            }
            case IN_PROGRESS -> {
                OffRampResponse response = new OffRampResponse(
                        OffRampDetails.KYC_IN_PROGRESS,
                        "NONE",
                        "NONE",
                        "NONE",
                        "NONE"
                );

                return BaseResponse.builder()
                        .data(response)
                        .message("kyc required")
                        .success(Boolean.TRUE)
                        .status(HttpStatus.OK)
                        .code(200).build();
            }
            case APPROVED -> {}
        }

        FiatCurrency fiatCurrency = fiatCurrencyService.getFiatCurrencyByUuid(request.fiatCurrencyUuid());

        CapaOffRampRequest offRampRequest = new CapaOffRampRequest(
            new CapaOffRampRequest.CapaUserBankInformationObject(
                userBankInformation.getClabe(),
                userBankInformation.getCountry()
            ),
            capaAccount.getUuid().toString(),
            BigDecimal.valueOf(request.amount()).multiply(BigDecimal.TEN.pow(tokenNetwork.getToken().getDecimals())).longValue(), // Convert to Long
            fiatCurrency.getSymbol(),
            tokenNetwork.getNetwork().getSymbol(),
            tokenNetwork.getToken().getSymbol(),
            0.03f
        );

        CapaOffRampResponse offRampResponse = capaService.createOffRamp(offRampRequest);

        OffRampResponse response = new OffRampResponse(
                OffRampDetails.SUCCESS,
                "NONE",
                offRampResponse.data().destinationWalletAddress(),
                request.amount().toString(),
                offRampResponse.data().fiatAmount().toString()
        );

        return BaseResponse.builder()
                .data(response)
                .message("Order placed successfully")
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .code(200).build();
    }

    private KYCStatus validateKyc(CapaKYCStatusResponse.KYCStatusResponse response) {
        int kycPoints = 0;

        CapaKYCStatusResponse.Media kycMedia = response.media();

        if (kycMedia.frontIdImageUrl() != null) {
            ++kycPoints;
        }

        if (kycMedia.backIdImageUrl() != null) {
            ++kycPoints;
        }

        if (kycMedia.selfieImageUrl() != null) {
            ++kycPoints;
        }

        if (kycMedia.selfieVideoUrl() != null) {
            ++kycPoints;
        }

        return switch (kycPoints) {
            case 0 -> KYCStatus.NOT_STARTED;
            case 1, 2, 3 -> KYCStatus.INCOMPLETE;
            case 4 -> {
                if (response.aipriseSummary().verificationResult().equals("APPROVED")) {
                    yield KYCStatus.APPROVED;
                }

                yield KYCStatus.IN_PROGRESS;
            }
            default -> throw new RuntimeException();
        };
    }

    private BaseResponse retryKYCGeneration(User user) {
        CapaAccount capaAccount = user.getCapaAccount();
        CapaKYCResponse kycResponse = generateKYC(
            user.getCapaAccount().getCountry().getValue(),
            capaAccount.getUuid()
        );

        OffRampResponse response = new OffRampResponse(
                OffRampDetails.INVALID_KYC,
                kycResponse.data().kycLink(),
                "NONE",
                "NONE",
                "NONE"
        );

        return BaseResponse.builder()
                .data(response)
                .message("KYC URL is needed, please redirect to the KYC URL")
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .code(200).build();
    }

    private BaseResponse createCapaAccount(User user) {
        CapaUserRequest userRequest = new CapaUserRequest(
                "INDIVIDUAL",
                user.getEmail(),
                user.getUuid()
        );

        CapaUserResponse userResponse = capaService.createUser(userRequest);
        if (!userResponse.success()) {
            throw new RuntimeException();
        }

        CapaAccount capaAccount = toCapaAccount();
        capaAccount.setUuid(UUID.fromString(userResponse.data().userId()));
        capaAccount.setUser(user);

        CapaAccount savedCapaAccount = capaAccountService.saveCapaAccount(capaAccount);

        user.setCapaAccount(savedCapaAccount);
        userService.updateUser(user);

        CapaKYCResponse kycResponse = generateKYC(capaAccount.getCountry().getValue(), capaAccount.getUuid());
        OffRampResponse response = new OffRampResponse(
                OffRampDetails.KYC_REQUIRED,
                kycResponse.data().kycLink(),
                "NONE",
                "NONE",
                "NONE"
        );

        return BaseResponse.builder()
                .data(response)
                .message("KYC URL is needed, please redirect to the KYC URL")
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .code(200).build();
    }

    private CapaKYCResponse generateKYC(String country, UUID userUuid) {
        CapaKYCRequest kycRequest = new CapaKYCRequest(
            country,
            "https://flow-fi-pos.firebaseapp.com/set-amount"
        );

        return capaService.generateKYC(kycRequest, userUuid);
    }

    private CapaAccount toCapaAccount() {
        CapaAccount capaAccount = new CapaAccount();

        capaAccount.setCountry(CapaSupportedCountries.MX);
        capaAccount.setKycStatus(KYCStatus.NOT_STARTED);
        capaAccount.setCreatedAt(LocalDateTime.now());

        return capaAccount;
    }

}
