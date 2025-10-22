package com.cb_labs.cb_flow_connect.service;

import com.cb_labs.cb_flow_connect.web.dto.response.BaseResponse;

import java.util.UUID;

public interface ITransactionHistoryService {
    BaseResponse getTransactionHistory(UUID userUuid);
}
