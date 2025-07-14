package com.cb_labs.cb_flow_connect.service;

import com.cb_labs.cb_flow_connect.persistance.entities.LiquidityProvider;
import com.cb_labs.cb_flow_connect.persistance.entities.Token;

import java.util.List;

public interface ILiquidityProviderTokenService {

    List<LiquidityProvider> getLiquidityProvidersByToken(Token token);

}
