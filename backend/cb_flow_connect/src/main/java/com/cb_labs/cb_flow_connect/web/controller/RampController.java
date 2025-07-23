package com.cb_labs.cb_flow_connect.web.controller;

import com.cb_labs.cb_flow_connect.persistance.entities.enums.ProviderImplementation;
import com.cb_labs.cb_flow_connect.persistance.entities.enums.RampType;
import com.cb_labs.cb_flow_connect.service.IRampService;
import com.cb_labs.cb_flow_connect.web.dto.request.OffRampRequest;
import com.cb_labs.cb_flow_connect.web.dto.request.OnRampRequest;
import com.cb_labs.cb_flow_connect.web.dto.response.BaseResponse;
import com.cb_labs.cb_flow_connect.web.dto.response.ClabeResponse;
import com.cb_labs.cb_flow_connect.web.dto.response.OffRampResponse;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("flow/")
public class RampController {

    @Autowired
    private IRampService service;

    @GetMapping("off-ramp/quoting/{liquidityProvider}")
    public ResponseEntity<BaseResponse> quoting(
        @PathVariable UUID liquidityProvider,
        @RequestParam UUID from,
        @RequestParam UUID to,
        @RequestParam String amount
    ) {
       return service.quote(liquidityProvider, from, to, RampType.OFF_RAMP, amount).apply();
    }

    @PostMapping("on-ramp")
    public ResponseEntity<BaseResponse<ClabeResponse>> onRamp(
        @RequestBody OnRampRequest request
    ) {
        return service.onRamp(request).apply();
    }

    @PostMapping("off-ramp")
    public ResponseEntity<BaseResponse<OffRampResponse>> offRamp(
        @RequestBody OffRampRequest request
    ) {
        return service.offRamp(request).apply();
    }
}
