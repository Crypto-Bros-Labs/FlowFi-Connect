package com.cb_labs.cb_flow_connect.service;

import com.cb_labs.cb_flow_connect.persistance.entities.LiquidityProvider;
import com.cb_labs.cb_flow_connect.persistance.entities.User;
import com.cb_labs.cb_flow_connect.persistance.entities.pivots.TokenNetwork;
import com.cb_labs.cb_flow_connect.web.dto.request.OnRampRequest;
import com.cb_labs.cb_flow_connect.web.dto.response.BaseResponse;

public interface IJunoRampFlowService {
    BaseResponse onRampFLow(User user, LiquidityProvider provider, TokenNetwork token, OnRampRequest request);
}
