package com.cb_labs.cb_flow_connect.service.impl;

import com.cb_labs.cb_flow_connect.persistance.entities.Clabe;
import com.cb_labs.cb_flow_connect.persistance.entities.LiquidityProvider;
import com.cb_labs.cb_flow_connect.persistance.entities.User;
import com.cb_labs.cb_flow_connect.persistance.respositories.IClabeRepository;
import com.cb_labs.cb_flow_connect.service.IClabeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClabeServiceImpl implements IClabeService {

    @Autowired
    private IClabeRepository repository;

    @Override
    public Clabe saveClabe(String clabe, User user, LiquidityProvider provider) {
        return repository.save(toClabe(clabe, user, provider));
    }

    @Override
    public Optional<Clabe> findClabe(User userId, LiquidityProvider provider) {
        return repository.findByUserAndProvider(userId, provider);
    }

    private Clabe toClabe(String clabeString, User user, LiquidityProvider provider) {
        Clabe clabe = new Clabe();

        clabe.setUuid(UUID.randomUUID());
        clabe.setClabe(clabeString);
        clabe.setUser(user);
        clabe.setProvider(provider);
        clabe.setCreatedAt(LocalDateTime.now());
        clabe.setUpdatedAt(LocalDateTime.now());

        return clabe;
    }
}
