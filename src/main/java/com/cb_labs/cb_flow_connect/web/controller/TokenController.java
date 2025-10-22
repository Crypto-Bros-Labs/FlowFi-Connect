package com.cb_labs.cb_flow_connect.web.controller;

import com.cb_labs.cb_flow_connect.service.ITokenService;
import com.cb_labs.cb_flow_connect.web.dto.response.BaseResponse;
import com.cb_labs.cb_flow_connect.web.dto.response.LiquidityProviderResponse;
import com.cb_labs.cb_flow_connect.web.dto.response.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("token")
public class TokenController {

    @Autowired
    private ITokenService service;

    @GetMapping()
    public ResponseEntity<BaseResponse<TokenResponse>> getAllTokens(
        @RequestParam(required = false) UUID network,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        return service.getAllTokens(network, page, size).apply();
    }

    @GetMapping("{tokenNetworkUuid}/providers")
    public ResponseEntity<BaseResponse<LiquidityProviderResponse>> getTokenLiquidityProviders(
        @PathVariable UUID tokenNetworkUuid
    ) {
        return service.getTokenLiquidityProviders(tokenNetworkUuid).apply();
    }

}
