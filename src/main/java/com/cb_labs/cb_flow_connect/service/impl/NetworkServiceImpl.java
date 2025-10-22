package com.cb_labs.cb_flow_connect.service.impl;

import com.cb_labs.cb_flow_connect.persistance.entities.Network;
import com.cb_labs.cb_flow_connect.persistance.respositories.INetworkRepository;
import com.cb_labs.cb_flow_connect.service.INetworkService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class NetworkServiceImpl implements INetworkService {

    @Autowired
    private INetworkRepository repository;

    @Override
    public Network getByUuid(UUID uuid) {
        return repository.findByUuid(uuid).orElseThrow(EntityNotFoundException::new);
    }
}
