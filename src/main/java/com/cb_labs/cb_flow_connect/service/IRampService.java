package com.cb_labs.cb_flow_connect.service;

import com.cb_labs.cb_flow_connect.persistance.entities.enums.ProviderImplementation;
import com.cb_labs.cb_flow_connect.persistance.entities.enums.RampType;
import com.cb_labs.cb_flow_connect.web.dto.request.OffRampRequest;
import com.cb_labs.cb_flow_connect.web.dto.request.OnRampRequest;
import com.cb_labs.cb_flow_connect.web.dto.response.BaseResponse;

import java.util.UUID;


public interface IRampService {
    BaseResponse onRamp(OnRampRequest request);
    BaseResponse offRamp(OffRampRequest request);
    BaseResponse quote(UUID implementation, UUID from, UUID to, RampType type, String amount);
}
