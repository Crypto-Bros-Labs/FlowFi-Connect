package com.cb_labs.cb_flow_connect.service.impl;

import com.cb_labs.cb_flow_connect.persistance.entities.LiquidityProvider;
import com.cb_labs.cb_flow_connect.persistance.entities.Token;
import com.cb_labs.cb_flow_connect.persistance.respositories.ITokenRepository;
import com.cb_labs.cb_flow_connect.service.ILiquidityProviderTokenService;
import com.cb_labs.cb_flow_connect.service.ITokenService;
import com.cb_labs.cb_flow_connect.web.dto.response.BaseResponse;
import com.cb_labs.cb_flow_connect.web.dto.response.LiquidityProviderResponse;
import com.cb_labs.cb_flow_connect.web.dto.response.TokenResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class TokenServiceImpl implements ITokenService {

    @Autowired
    private ITokenRepository repository;

    @Autowired
    private ILiquidityProviderTokenService liquidityProviderTokenService;

    @Override
    public Token findOneAndEnsureExists(UUID uuid) {
        return repository.findByUuid(uuid).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public BaseResponse getAllTokens(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<TokenResponse> tokensPage = repository.findAll(pageable)
                .map(this::toTokenResponse);

        return BaseResponse.builder()
                .data(tokensPage)
                .message("Tokens retrieved paginated")
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .code(200).build();
    }

    @Override
    public BaseResponse getTokenLiquidityProviders(UUID tokenUuid) {
        Token token = findOneAndEnsureExists(tokenUuid);

        List<LiquidityProviderResponse> providers = liquidityProviderTokenService
                .getLiquidityProvidersByToken(token).stream()
                .map(this::toLiquidityProviderResponse).toList();

        return BaseResponse.builder()
                .data(providers)
                .message("Liquidity providers retrieved")
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .code(200).build();
    }

    private TokenResponse toTokenResponse(Token token) {
        return new TokenResponse(
            token.getUuid(),
            token.getName(),
            token.getNetwork(),
            token.getAddress()
        );
    }

    private LiquidityProviderResponse toLiquidityProviderResponse(LiquidityProvider provider) {
        return new LiquidityProviderResponse(
            provider.getUuid(),
            provider.getName()
        );
    }
}
