package com.cb_labs.cb_flow_connect.web.controller;

import com.cb_labs.cb_flow_connect.service.IAuthService;
import com.cb_labs.cb_flow_connect.web.dto.response.AuthResponse;
import com.cb_labs.cb_flow_connect.web.dto.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private IAuthService service;

    @GetMapping("/{userEmail}")
    public ResponseEntity<BaseResponse<AuthResponse>> generateAuth(@PathVariable String userEmail) {
        return service.authUser(userEmail).apply();
    }

}
