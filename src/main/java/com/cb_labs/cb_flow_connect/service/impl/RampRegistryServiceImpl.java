package com.cb_labs.cb_flow_connect.service.impl;

import com.cb_labs.cb_flow_connect.persistance.entities.LiquidityProvider;
import com.cb_labs.cb_flow_connect.persistance.entities.RampRegistry;
import com.cb_labs.cb_flow_connect.persistance.entities.Token;
import com.cb_labs.cb_flow_connect.persistance.entities.User;
import com.cb_labs.cb_flow_connect.persistance.respositories.IRampRegistryRepository;
import com.cb_labs.cb_flow_connect.service.IRampRegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RampRegistryServiceImpl implements IRampRegistryService {

    @Autowired
    private IRampRegistryRepository repository;

    @Override
    public void addRegistry(User user, LiquidityProvider provider, Token token, Double amount) {
        repository.save(toRampRegistry(user, provider, token, amount));
    }

    private RampRegistry toRampRegistry(User user, LiquidityProvider provider, Token token, Double amount) {
        RampRegistry registry = new RampRegistry();

        registry.setUuid(UUID.randomUUID());
        registry.setAmount(amount);
        registry.setUser(user);
        registry.setToken(token);
        registry.setLiquidityProvider(provider);
        registry.setCreatedAt(LocalDateTime.now());

        return registry;
    }
}
