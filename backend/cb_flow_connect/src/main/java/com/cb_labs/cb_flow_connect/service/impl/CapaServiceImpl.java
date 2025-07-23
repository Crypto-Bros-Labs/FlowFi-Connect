package com.cb_labs.cb_flow_connect.service.impl;

import com.cb_labs.cb_flow_connect.configurations.CapaConfig;
import com.cb_labs.cb_flow_connect.service.ICapaService;
import com.cb_labs.cb_flow_connect.web.dto.models.capa.request.CapaKYCRequest;
import com.cb_labs.cb_flow_connect.web.dto.models.capa.request.CapaKYCStatusResponse;
import com.cb_labs.cb_flow_connect.web.dto.models.capa.request.CapaOffRampRequest;
import com.cb_labs.cb_flow_connect.web.dto.models.capa.request.CapaQuotingRequest;
import com.cb_labs.cb_flow_connect.web.dto.models.capa.request.CapaUserRequest;
import com.cb_labs.cb_flow_connect.web.dto.models.capa.response.CapaKYCResponse;
import com.cb_labs.cb_flow_connect.web.dto.models.capa.response.CapaOffRampResponse;
import com.cb_labs.cb_flow_connect.web.dto.models.capa.response.CapaQuotingResponse;
import com.cb_labs.cb_flow_connect.web.dto.models.capa.response.CapaUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class CapaServiceImpl implements ICapaService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CapaConfig capaConfig;

    @Override
    public CapaUserResponse createUser(CapaUserRequest request) {
        HttpEntity<CapaUserRequest> entity = new HttpEntity<>(request, getHeaders());

        ResponseEntity<CapaUserResponse> response = restTemplate.exchange(
                capaConfig.getBaseUrl() + "/users",
                HttpMethod.POST,
                entity,
                CapaUserResponse.class
        );

        if (!response.hasBody()) {
            throw new RuntimeException();
        }

        return response.getBody();
    }

    @Override
    public CapaKYCResponse generateKYC(CapaKYCRequest request, UUID userId) {
        HttpEntity<CapaKYCRequest> entity = new HttpEntity<>(request, getHeaders());

        ResponseEntity<CapaKYCResponse> response = restTemplate.exchange(
                capaConfig.getBaseUrl() + "/users/" + userId + "/kyc/verification-link",
                HttpMethod.POST,
                entity,
                CapaKYCResponse.class
        );

        if (!response.hasBody()) {
            throw new RuntimeException();
        }

        return response.getBody();
    }

    @Override
    public CapaKYCStatusResponse getKYCStatus(UUID userId) {
        HttpEntity<Void> entity = new HttpEntity<>(getHeaders());

        ResponseEntity<CapaKYCStatusResponse> response = restTemplate.exchange(
                capaConfig.getBaseUrl() + "/kyc/details?userId=" + userId,
                HttpMethod.GET,
                entity,
                CapaKYCStatusResponse.class
        );

        if (!response.hasBody()) {
            throw new RuntimeException();
        }

        return response.getBody();
    }

    @Override
    public CapaQuotingResponse quoting(CapaQuotingRequest request) {
        HttpEntity<Void> entity = new HttpEntity<>(getHeaders());

        ResponseEntity<CapaQuotingResponse> response = restTemplate.exchange(
                capaConfig.getBaseUrl() + "/quotes?" + request.toQueryParams(),
                HttpMethod.GET,
                entity,
                CapaQuotingResponse.class
        );

        if (!response.hasBody()) {
            throw new RuntimeException();
        }

        return response.getBody();
    }

    @Override
    public CapaOffRampResponse createOffRamp(CapaOffRampRequest request) {
        HttpEntity<CapaOffRampRequest> entity = new HttpEntity<>(request, getHeaders());

        ResponseEntity<CapaOffRampResponse> response = restTemplate.exchange(
                capaConfig.getBaseUrl() + "/off-ramp",
                HttpMethod.POST,
                entity,
                CapaOffRampResponse.class
        );

        if (!response.hasBody()) {
            throw new RuntimeException();
        }

        return response.getBody();
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("partner-api-key", capaConfig.getApiKey());

        return headers;
    }
}
