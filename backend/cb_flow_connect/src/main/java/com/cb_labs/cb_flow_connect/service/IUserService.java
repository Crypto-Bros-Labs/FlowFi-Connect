package com.cb_labs.cb_flow_connect.service;

import com.cb_labs.cb_flow_connect.persistance.entities.User;
import com.cb_labs.cb_flow_connect.web.dto.request.UserRequest;
import com.cb_labs.cb_flow_connect.web.dto.response.BaseResponse;

public interface IUserService {
    User getUserByValidAuthCode(String authCode);
    User getOrCreate(String email);
    BaseResponse updateUser(UserRequest request);
}
