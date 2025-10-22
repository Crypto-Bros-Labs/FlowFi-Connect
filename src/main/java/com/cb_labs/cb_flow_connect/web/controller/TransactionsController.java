package com.cb_labs.cb_flow_connect.web.controller;

import com.cb_labs.cb_flow_connect.service.ITransactionHistoryService;
import com.cb_labs.cb_flow_connect.web.dto.response.BaseResponse;
import com.cb_labs.cb_flow_connect.web.dto.response.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("transactions")
public class TransactionsController {

    @Autowired
    private ITransactionHistoryService service;

    @GetMapping("user/{userUuid}")
    public ResponseEntity<BaseResponse<TransactionResponse>> getTransactions(
        @PathVariable UUID userUuid
    ) {
        return service.getTransactionHistory(userUuid).apply();
    }

}
