package com.cb_labs.cb_flow_connect.service;

import com.cb_labs.cb_flow_connect.persistance.entities.LiquidityProvider;
import com.cb_labs.cb_flow_connect.persistance.entities.Token;
import com.cb_labs.cb_flow_connect.persistance.entities.pivots.TokenNetwork;

import java.util.UUID;

public interface ILiquidityProviderService {
    LiquidityProvider getLiquidityProvider(UUID providerUuid);
    LiquidityProvider findByUuidAndToken(UUID uuid, TokenNetwork token);
}
