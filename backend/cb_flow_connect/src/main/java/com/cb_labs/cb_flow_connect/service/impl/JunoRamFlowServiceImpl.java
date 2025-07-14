package com.cb_labs.cb_flow_connect.service.impl;

import com.cb_labs.cb_flow_connect.persistance.entities.Clabe;
import com.cb_labs.cb_flow_connect.persistance.entities.LiquidityProvider;
import com.cb_labs.cb_flow_connect.persistance.entities.Token;
import com.cb_labs.cb_flow_connect.persistance.entities.User;
import com.cb_labs.cb_flow_connect.service.IClabeService;
import com.cb_labs.cb_flow_connect.service.IJunoRampFlowService;
import com.cb_labs.cb_flow_connect.service.IJunoService;
import com.cb_labs.cb_flow_connect.service.IRampRegistryService;
import com.cb_labs.cb_flow_connect.web.dto.models.juno.response.JunoClabeResponse;
import com.cb_labs.cb_flow_connect.web.dto.request.OnRampRequest;
import com.cb_labs.cb_flow_connect.web.dto.response.BaseResponse;
import com.cb_labs.cb_flow_connect.web.dto.response.ClabeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class JunoRamFlowServiceImpl implements IJunoRampFlowService {

    @Autowired
    private IClabeService clabeService;

    @Autowired
    private IJunoService junoService;

    @Autowired
    private IRampRegistryService rampRegistryService;

    @Override
    public BaseResponse onRampFLow(User user, LiquidityProvider provider, Token token, OnRampRequest request) {
        Clabe clabe = clabeService.findClabe(user, provider)
                .orElseGet(() -> createAndSaveClabe(user, provider));

        rampRegistryService.addRegistry(user, provider, token, request.amount());

        return BaseResponse.builder()
                .data(toClabeResponse(clabe))
                .message("Clabe retrieved")
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .code(200).build();
    }

    private Clabe createAndSaveClabe(User user, LiquidityProvider provider) {
        JunoClabeResponse clabeResponse = junoService.createClabe();
        return clabeService.saveClabe(clabeResponse.payload().clabe(), user, provider);
    }

    private ClabeResponse toClabeResponse(Clabe clabe) {
        return new ClabeResponse(
            clabe.getUuid(),
            clabe.getClabe()
        );
    }
}
