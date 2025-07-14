package com.cb_labs.cb_flow_connect.service.impl;

import com.cb_labs.cb_flow_connect.persistance.entities.LiquidityProvider;
import com.cb_labs.cb_flow_connect.persistance.entities.Token;
import com.cb_labs.cb_flow_connect.persistance.entities.User;
import com.cb_labs.cb_flow_connect.service.IJunoRampFlowService;
import com.cb_labs.cb_flow_connect.service.ILiquidityProviderService;
import com.cb_labs.cb_flow_connect.service.IRampService;
import com.cb_labs.cb_flow_connect.service.ITokenService;
import com.cb_labs.cb_flow_connect.service.IUserService;
import com.cb_labs.cb_flow_connect.web.dto.request.OnRampRequest;
import com.cb_labs.cb_flow_connect.web.dto.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RampServiceImpl implements IRampService {

    @Autowired
    private IUserService userService;

    @Autowired
    private ILiquidityProviderService liquidityProviderService;

    @Autowired
    private IJunoRampFlowService junoRampFlowService;

    @Autowired
    private ITokenService tokenService;

    @Override
    public BaseResponse onRamp(OnRampRequest request) {
        Token token = tokenService.findOneAndEnsureExists(request.tokenUuid());
        LiquidityProvider provider = liquidityProviderService.findByUuidAndToken(request.providerUuid(), token);

        User user = userService.getUserByUuid(request.userUuid());

        return switch (provider.getImplementation()) {
            case JUNO -> junoRampFlowService.onRampFLow(user, provider, token, request);
        };
    }
}
