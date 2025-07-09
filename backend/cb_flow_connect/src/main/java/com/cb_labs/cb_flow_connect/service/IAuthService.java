package com.cb_labs.cb_flow_connect.service;

import com.cb_labs.cb_flow_connect.web.dto.response.BaseResponse;

public interface IAuthService {
    BaseResponse authUser(String email);
}
