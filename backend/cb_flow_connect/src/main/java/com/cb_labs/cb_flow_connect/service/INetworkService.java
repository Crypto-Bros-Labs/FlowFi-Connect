package com.cb_labs.cb_flow_connect.service;

import com.cb_labs.cb_flow_connect.persistance.entities.Network;

import java.util.UUID;

public interface INetworkService {
    Network getByUuid(UUID uuid);
}
