package com.cb_labs.cb_flow_connect.service.impl;

import com.cb_labs.cb_flow_connect.persistance.entities.CapaAccount;
import com.cb_labs.cb_flow_connect.persistance.respositories.ICapaAccountRepository;
import com.cb_labs.cb_flow_connect.service.ICapaAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CapaAccountServiceImpl implements ICapaAccountService {

    @Autowired
    private ICapaAccountRepository repository;

    @Override
    public CapaAccount saveCapaAccount(CapaAccount capaAccount) {
        return repository.save(capaAccount);
    }
}
