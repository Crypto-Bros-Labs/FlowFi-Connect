package com.cb_labs.cb_flow_connect.service;

import com.cb_labs.cb_flow_connect.persistance.entities.Network;
import com.cb_labs.cb_flow_connect.persistance.entities.pivots.TokenNetwork;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;


public interface ITokenNetworkService {
    TokenNetwork getTokenNetworkByUuid(UUID tokenNetworkUuid);
    Page<TokenNetwork> getAllTokens(Network network, Pageable pageRequest);
}
