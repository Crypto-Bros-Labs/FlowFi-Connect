package com.cb_labs.cb_flow_connect.service;

import com.cb_labs.cb_flow_connect.persistance.entities.Clabe;
import com.cb_labs.cb_flow_connect.persistance.entities.LiquidityProvider;
import com.cb_labs.cb_flow_connect.persistance.entities.User;

import java.util.Optional;

public interface IClabeService {
    Clabe saveClabe(String clabe, User userUuid, LiquidityProvider provider);
    Optional<Clabe> findClabe(User userUuid, LiquidityProvider provider);
}
