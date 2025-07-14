package com.cb_labs.cb_flow_connect.service;

import com.cb_labs.cb_flow_connect.web.dto.request.OnRampRequest;
import com.cb_labs.cb_flow_connect.web.dto.response.BaseResponse;


public interface IRampService {
    BaseResponse onRamp(OnRampRequest request);
}
