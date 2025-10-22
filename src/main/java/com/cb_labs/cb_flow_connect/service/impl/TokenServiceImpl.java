package com.cb_labs.cb_flow_connect.service.impl;

import com.cb_labs.cb_flow_connect.persistance.entities.LiquidityProvider;
import com.cb_labs.cb_flow_connect.persistance.entities.Network;
import com.cb_labs.cb_flow_connect.persistance.entities.Token;
import com.cb_labs.cb_flow_connect.persistance.entities.pivots.TokenNetwork;
import com.cb_labs.cb_flow_connect.persistance.respositories.ITokenRepository;
import com.cb_labs.cb_flow_connect.service.ILiquidityProviderTokenService;
import com.cb_labs.cb_flow_connect.service.INetworkService;
import com.cb_labs.cb_flow_connect.service.ITokenNetworkService;
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

    @Autowired
    private ITokenNetworkService tokenNetworkService;

    @Autowired
    private INetworkService networkService;

    @Override
    public Token findOneAndEnsureExists(UUID uuid) {
        return repository.findByUuid(uuid).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public BaseResponse getAllTokens(UUID networkUuid, int page, int size) {
        Network network = null;
        if (networkUuid != null) {
            network = networkService.getByUuid(networkUuid);
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<TokenResponse> tokensPage = tokenNetworkService.getAllTokens(network, pageable)
                .map(this::toTokenResponse);

        return BaseResponse.builder()
                .data(tokensPage)
                .message("Tokens retrieved paginated")
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .code(200).build();
    }

    @Override
    public BaseResponse getTokenLiquidityProviders(UUID tokenNetworkUuid) {
        TokenNetwork tokenNetwork = tokenNetworkService.getTokenNetworkByUuid(tokenNetworkUuid);

        List<LiquidityProviderResponse> providers = liquidityProviderTokenService
                .getLiquidityProvidersByToken(tokenNetwork).stream()
                .map(this::toLiquidityProviderResponse).toList();

        return BaseResponse.builder()
                .data(providers)
                .message("Liquidity providers retrieved")
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .code(200).build();
    }

    private TokenResponse toTokenResponse(TokenNetwork tokenNetwork) {
        return new TokenResponse(
            tokenNetwork.getUuid(),
            tokenNetwork.getToken().getSymbol(),
            tokenNetwork.getNetwork().getName(),
            tokenNetwork.getToken().getAddress(),
            tokenNetwork.getToken().getImageUrl()
        );
    }

    private LiquidityProviderResponse toLiquidityProviderResponse(LiquidityProvider provider) {
        return new LiquidityProviderResponse(
            provider.getUuid(),
            provider.getName()
        );
    }
}
