package com.cb_labs.cb_flow_connect.service.impl;

import com.cb_labs.cb_flow_connect.persistance.entities.LiquidityProvider;
import com.cb_labs.cb_flow_connect.persistance.entities.Token;
import com.cb_labs.cb_flow_connect.persistance.entities.pivots.LiquidityProviderToken;
import com.cb_labs.cb_flow_connect.persistance.respositories.ILiquidityProviderTokenRepository;
import com.cb_labs.cb_flow_connect.service.ILiquidityProviderTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiquidityTokenProviderServiceImpl implements ILiquidityProviderTokenService {

    @Autowired
    private ILiquidityProviderTokenRepository repository;

    @Override
    public List<LiquidityProvider> getLiquidityProvidersByToken(Token token) {
        return repository.findByToken(token)
                .stream().map(LiquidityProviderToken::getLiquidityProvider).toList();
    }
}
