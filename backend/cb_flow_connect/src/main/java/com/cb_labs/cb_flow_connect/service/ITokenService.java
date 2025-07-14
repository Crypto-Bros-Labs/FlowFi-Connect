package com.cb_labs.cb_flow_connect.service;

import com.cb_labs.cb_flow_connect.persistance.entities.Token;
import com.cb_labs.cb_flow_connect.web.dto.response.BaseResponse;

import java.util.UUID;

public interface ITokenService {
    Token findOneAndEnsureExists(UUID uuid);
    BaseResponse getAllTokens(int page, int size);
    BaseResponse getTokenLiquidityProviders(UUID tokenUuid);
}
