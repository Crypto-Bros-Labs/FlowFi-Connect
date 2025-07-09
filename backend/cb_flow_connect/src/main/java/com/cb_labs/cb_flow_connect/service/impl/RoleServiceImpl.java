package com.cb_labs.cb_flow_connect.service.impl;

import com.cb_labs.cb_flow_connect.persistance.entities.Role;
import com.cb_labs.cb_flow_connect.persistance.respositories.IRoleRepository;
import com.cb_labs.cb_flow_connect.service.IRoleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleRepository repository;

    @Override
    public Role getRoleById(Integer roleId) {
        return repository.getRoleById(roleId).orElseThrow(EntityNotFoundException::new);
    }
}
