package com.cb_labs.cb_flow_connect.service;

import com.cb_labs.cb_flow_connect.web.dto.models.capa.request.CapaKYCRequest;
import com.cb_labs.cb_flow_connect.web.dto.models.capa.request.CapaKYCStatusResponse;
import com.cb_labs.cb_flow_connect.web.dto.models.capa.request.CapaOffRampRequest;
import com.cb_labs.cb_flow_connect.web.dto.models.capa.request.CapaQuotingRequest;
import com.cb_labs.cb_flow_connect.web.dto.models.capa.request.CapaUserRequest;
import com.cb_labs.cb_flow_connect.web.dto.models.capa.response.CapaKYCResponse;
import com.cb_labs.cb_flow_connect.web.dto.models.capa.response.CapaOffRampResponse;
import com.cb_labs.cb_flow_connect.web.dto.models.capa.response.CapaQuotingResponse;
import com.cb_labs.cb_flow_connect.web.dto.models.capa.response.CapaTransactionHistoryResponse;
import com.cb_labs.cb_flow_connect.web.dto.models.capa.response.CapaUserResponse;

import java.util.UUID;

public interface ICapaService {
    CapaUserResponse createUser(CapaUserRequest request);
    CapaTransactionHistoryResponse getTransactions(UUID userId);
    CapaKYCResponse generateKYC(CapaKYCRequest request, UUID userId);
    CapaKYCStatusResponse getKYCStatus(UUID userId);
    CapaQuotingResponse quoting(CapaQuotingRequest request);
    CapaOffRampResponse createOffRamp(CapaOffRampRequest request);
}
