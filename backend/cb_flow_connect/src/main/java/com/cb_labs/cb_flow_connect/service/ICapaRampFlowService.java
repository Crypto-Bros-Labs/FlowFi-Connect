package com.cb_labs.cb_flow_connect.service;

import com.cb_labs.cb_flow_connect.persistance.entities.LiquidityProvider;
import com.cb_labs.cb_flow_connect.persistance.entities.User;
import com.cb_labs.cb_flow_connect.persistance.entities.enums.RampType;
import com.cb_labs.cb_flow_connect.persistance.entities.pivots.TokenNetwork;
import com.cb_labs.cb_flow_connect.web.dto.request.OffRampRequest;
import com.cb_labs.cb_flow_connect.web.dto.response.BaseResponse;

import java.util.UUID;

public interface ICapaRampFlowService {
    BaseResponse quoting(TokenNetwork tokenNetwork, RampType rampType, UUID fiatUuid, Long cryptoAmount, Double fiatAmount);
    BaseResponse offRampFlow(User user, LiquidityProvider provider, TokenNetwork token, OffRampRequest request);
}
