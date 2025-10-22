package com.cb_labs.cb_flow_connect.service;

import com.cb_labs.cb_flow_connect.persistance.entities.AuthCode;
import com.cb_labs.cb_flow_connect.persistance.entities.User;

public interface IAuthCodeService {
    AuthCode generateAuthCode(User user);
    void validateAndDeleteCode(String code);
}
