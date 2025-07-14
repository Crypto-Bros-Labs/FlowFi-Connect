package com.cb_labs.cb_flow_connect.web.controller;

import com.cb_labs.cb_flow_connect.service.IRampService;
import com.cb_labs.cb_flow_connect.web.dto.request.OnRampRequest;
import com.cb_labs.cb_flow_connect.web.dto.response.BaseResponse;
import com.cb_labs.cb_flow_connect.web.dto.response.ClabeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("flow/")
public class RampController {

    @Autowired
    private IRampService service;

    @PostMapping("on-ramp")
    public ResponseEntity<BaseResponse<ClabeResponse>> onRamp(
        @RequestBody OnRampRequest request
    ) {
        return service.onRamp(request).apply();
    }
}
