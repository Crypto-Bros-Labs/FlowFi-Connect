package com.cb_labs.cb_flow_connect.service.impl;

import com.cb_labs.cb_flow_connect.persistance.entities.Network;
import com.cb_labs.cb_flow_connect.persistance.entities.pivots.TokenNetwork;
import com.cb_labs.cb_flow_connect.persistance.respositories.ITokenNetworkRepository;
import com.cb_labs.cb_flow_connect.service.ITokenNetworkService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.cb_labs.cb_flow_connect.persistance.specifications.TokenNetworkSpecifications.networkEquals;

@Service
public class TokenNetworkServiceImpl implements ITokenNetworkService {

    @Autowired
    private ITokenNetworkRepository repository;

    @Override
    public TokenNetwork getTokenNetworkByUuid(UUID tokenNetworkUuid) {
        return repository.findByUuid(tokenNetworkUuid).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<TokenNetwork> getAllTokens(Network network, Pageable pageRequest) {
        return repository.findAll(createSpecs(network), pageRequest);
    }

    private Specification<TokenNetwork> createSpecs(Network network) {
        return networkEquals(network);
    }
}
