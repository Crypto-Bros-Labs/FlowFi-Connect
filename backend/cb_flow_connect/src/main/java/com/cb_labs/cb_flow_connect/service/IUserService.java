package com.cb_labs.cb_flow_connect.service;

import com.cb_labs.cb_flow_connect.persistance.entities.User;
import com.cb_labs.cb_flow_connect.web.dto.request.UserRequest;
import com.cb_labs.cb_flow_connect.web.dto.response.BaseResponse;

import java.util.UUID;

public interface IUserService {
    User getUserByUuid(UUID uuid);
    User getUserByValidAuthCode(String authCode);
    User getOrCreate(String email);
    User updateUser(User user);
    BaseResponse updateUser(UserRequest request);
}
