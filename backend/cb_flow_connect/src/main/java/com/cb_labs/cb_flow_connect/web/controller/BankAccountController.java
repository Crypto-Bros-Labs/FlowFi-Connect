package com.cb_labs.cb_flow_connect.web.controller;

import com.cb_labs.cb_flow_connect.service.IUserBankInformationService;
import com.cb_labs.cb_flow_connect.web.dto.request.UserBankInformationRequest;
import com.cb_labs.cb_flow_connect.web.dto.response.BaseResponse;
import com.cb_labs.cb_flow_connect.web.dto.response.UserBankInformationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bank-information")
public class BankAccountController {

    @Autowired
    private IUserBankInformationService service;

    @GetMapping("/user/{userUuid}")
    public ResponseEntity<BaseResponse<List<UserBankInformationResponse>>> getAllByUser(
        @PathVariable UUID userUuid
    ) {
        return service.getAllByUserUuid(userUuid).apply();
    }

    @PostMapping
    public ResponseEntity<BaseResponse<UserBankInformationResponse>> createBankInformation(
        @RequestBody UserBankInformationRequest request
    ) {
       return service.createUserBankInformation(request).apply();
    }

}
