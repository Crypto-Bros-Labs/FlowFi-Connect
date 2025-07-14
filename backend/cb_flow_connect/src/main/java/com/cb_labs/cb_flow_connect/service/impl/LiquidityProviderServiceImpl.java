package com.cb_labs.cb_flow_connect.service.impl;

import com.cb_labs.cb_flow_connect.persistance.entities.LiquidityProvider;
import com.cb_labs.cb_flow_connect.persistance.entities.Token;
import com.cb_labs.cb_flow_connect.persistance.respositories.ILiquidityProviderRepository;
import com.cb_labs.cb_flow_connect.service.ILiquidityProviderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LiquidityProviderServiceImpl implements ILiquidityProviderService {

    @Autowired
    private ILiquidityProviderRepository repository;

    @Override
    public LiquidityProvider getLiquidityProvider(UUID providerUuid) {
        return repository.findByUuid(providerUuid).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public LiquidityProvider findByUuidAndToken(UUID uuid, Token token) {
        return repository.findByUuidToken(uuid, token).orElseThrow(EntityNotFoundException::new);
    }

}
