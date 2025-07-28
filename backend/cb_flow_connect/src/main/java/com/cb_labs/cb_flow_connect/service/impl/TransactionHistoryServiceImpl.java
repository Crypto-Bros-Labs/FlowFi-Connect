package com.cb_labs.cb_flow_connect.service.impl;

import com.cb_labs.cb_flow_connect.persistance.entities.User;
import com.cb_labs.cb_flow_connect.persistance.entities.enums.RampType;
import com.cb_labs.cb_flow_connect.service.ICapaService;
import com.cb_labs.cb_flow_connect.service.ITransactionHistoryService;
import com.cb_labs.cb_flow_connect.service.IUserService;
import com.cb_labs.cb_flow_connect.web.dto.models.capa.response.CapaTransactionHistoryResponse.TransactionHistoryResponse;
import com.cb_labs.cb_flow_connect.web.dto.response.BaseResponse;
import com.cb_labs.cb_flow_connect.web.dto.response.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionHistoryServiceImpl implements ITransactionHistoryService {

    @Autowired
    private ICapaService capaService;

    @Autowired
    private IUserService userService;

    @Override
    public BaseResponse getTransactionHistory(UUID userUuid) {
        User user = userService.getUserByUuid(userUuid);

        List<TransactionResponse> responses = capaService.getTransactions(user.getCapaAccount().getUuid())
                .data().data().stream().map(this::toTransactionResponse).toList();

        return BaseResponse.builder()
                .data(responses)
                .message("Data has been retrieved")
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .code(200).build();
    }

    private TransactionResponse toTransactionResponse(TransactionHistoryResponse.TransactionResponse response) {
        return new TransactionResponse(
            RampType.valueOf(response.type()),
            response.createdAt(),
            response.status(),
            BigDecimal.valueOf(response.cryptoAmount()).toPlainString(),
            BigDecimal.valueOf(response.fiatAmount()).toPlainString()
        );
    }
}
