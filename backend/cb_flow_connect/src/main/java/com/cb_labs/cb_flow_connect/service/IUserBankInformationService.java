package com.cb_labs.cb_flow_connect.service;

import com.cb_labs.cb_flow_connect.persistance.entities.UserBankInformation;
import com.cb_labs.cb_flow_connect.web.dto.request.UserBankInformationRequest;
import com.cb_labs.cb_flow_connect.web.dto.response.BaseResponse;

import java.util.UUID;

public interface IUserBankInformationService {
    UserBankInformation getById(UUID uuid);
    BaseResponse getAllByUserUuid(UUID userUuid);
    BaseResponse createUserBankInformation(UserBankInformationRequest request);
}
