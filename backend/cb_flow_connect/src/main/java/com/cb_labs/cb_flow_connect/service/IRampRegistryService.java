package com.cb_labs.cb_flow_connect.service;

import com.cb_labs.cb_flow_connect.persistance.entities.LiquidityProvider;
import com.cb_labs.cb_flow_connect.persistance.entities.Token;
import com.cb_labs.cb_flow_connect.persistance.entities.User;

public interface IRampRegistryService {
    void addRegistry(User user, LiquidityProvider provider, Token token, Double amount);
}
