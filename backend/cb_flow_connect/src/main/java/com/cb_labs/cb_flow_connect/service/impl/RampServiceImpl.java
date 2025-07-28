package com.cb_labs.cb_flow_connect.service.impl;

import com.cb_labs.cb_flow_connect.persistance.entities.LiquidityProvider;
import com.cb_labs.cb_flow_connect.persistance.entities.Token;
import com.cb_labs.cb_flow_connect.persistance.entities.User;
import com.cb_labs.cb_flow_connect.persistance.entities.enums.ProviderImplementation;
import com.cb_labs.cb_flow_connect.persistance.entities.enums.RampType;
import com.cb_labs.cb_flow_connect.persistance.entities.pivots.TokenNetwork;
import com.cb_labs.cb_flow_connect.service.ICapaRampFlowService;
import com.cb_labs.cb_flow_connect.service.IJunoRampFlowService;
import com.cb_labs.cb_flow_connect.service.ILiquidityProviderService;
import com.cb_labs.cb_flow_connect.service.IRampService;
import com.cb_labs.cb_flow_connect.service.ITokenNetworkService;
import com.cb_labs.cb_flow_connect.service.ITokenService;
import com.cb_labs.cb_flow_connect.service.IUserService;
import com.cb_labs.cb_flow_connect.web.dto.request.OffRampRequest;
import com.cb_labs.cb_flow_connect.web.dto.request.OnRampRequest;
import com.cb_labs.cb_flow_connect.web.dto.response.BaseResponse;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RampServiceImpl implements IRampService {

    @Autowired
    private IUserService userService;

    @Autowired
    private ITokenService tokenService;

    @Autowired
    private ILiquidityProviderService liquidityProviderService;

    @Autowired
    private IJunoRampFlowService junoRampFlowService;

    @Autowired
    private ICapaRampFlowService capaRampFlowService;

    @Autowired
    private ITokenNetworkService tokenNetworkService;

    @Override
    public BaseResponse onRamp(OnRampRequest request) {
        TokenNetwork tokenNetwork = tokenNetworkService.getTokenNetworkByUuid(request.tokenNetworUuid());
        LiquidityProvider provider = liquidityProviderService.findByUuidAndToken(request.providerUuid(), tokenNetwork);

        User user = userService.getUserByUuid(request.userUuid());

        return switch (provider.getImplementation()) {
            case JUNO -> junoRampFlowService.onRampFLow(user, provider, tokenNetwork, request);
            case CAPA -> throw new NotImplementedException();
        };
    }

    @Override
    public BaseResponse offRamp(OffRampRequest request) {
        TokenNetwork tokenNetwork = tokenNetworkService.getTokenNetworkByUuid(request.tokenNetworkUuid());
        LiquidityProvider provider = liquidityProviderService.findByUuidAndToken(request.providerUuid(), tokenNetwork);

        User user = userService.getUserByUuid(request.userUuid());

        return switch (provider.getImplementation()) {
            case JUNO -> throw new NotImplementedException();
            case CAPA -> capaRampFlowService.offRampFlow(user, provider, tokenNetwork, request);
        };
    }

    @Override
    public BaseResponse quote(UUID liquidityProvider, UUID from, UUID to, RampType type, String amount) {
        TokenNetwork tokenNetwork = null;
        UUID fiatUuid = null;
        switch (type) {
            case ON_RAMP -> throw new NotImplementedException();
            case OFF_RAMP -> {
                tokenNetwork = tokenNetworkService.getTokenNetworkByUuid(from);
                fiatUuid = to;
            }
        }

        LiquidityProvider provider = liquidityProviderService.findByUuidAndToken(liquidityProvider, tokenNetwork);
        return switch (provider.getImplementation()) {
            case JUNO -> throw new NotImplementedException();
            case CAPA -> capaRampFlowService.quoting(tokenNetwork, type, fiatUuid, null, Double.valueOf(amount));
        };
    }
}
