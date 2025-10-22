package com.cb_labs.cb_flow_connect.service.impl;

import com.cb_labs.cb_flow_connect.persistance.entities.User;
import com.cb_labs.cb_flow_connect.persistance.entities.UserBankInformation;
import com.cb_labs.cb_flow_connect.persistance.respositories.IUserBankInformationRepository;
import com.cb_labs.cb_flow_connect.service.IUserBankInformationService;
import com.cb_labs.cb_flow_connect.service.IUserService;
import com.cb_labs.cb_flow_connect.web.dto.request.UserBankInformationRequest;
import com.cb_labs.cb_flow_connect.web.dto.response.BaseResponse;
import com.cb_labs.cb_flow_connect.web.dto.response.UserBankInformationResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserBankInformationServiceImpl implements IUserBankInformationService {

    @Autowired
    private IUserBankInformationRepository repository;

    @Autowired
    private IUserService userService;

    @Override
    public UserBankInformation getById(UUID uuid) {
        return repository.findByUuid(uuid).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public BaseResponse getAllByUserUuid(UUID userUuid) {
        User user = userService.getUserByUuid(userUuid);

        List<UserBankInformationResponse> responses = repository.findAllByUser(user)
                .stream().map(this::toUserBankInformationResponse).toList();

        return BaseResponse.builder()
                .data(responses)
                .message("User bank accounts retrieved successfully")
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .code(200).build();
    }

    @Override
    public BaseResponse createUserBankInformation(UserBankInformationRequest request) {
        User user = userService.getUserByUuid(request.userUuid());
        UserBankInformation userBankInformation = toUserBankInformation(request);
        userBankInformation.setUser(user);

        repository.save(userBankInformation);

        return BaseResponse.builder()
                .data(toUserBankInformationResponse(userBankInformation))
                .message("User bank information created successfully")
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .code(200).build();
    }

    private UserBankInformation toUserBankInformation(UserBankInformationRequest request) {
        UserBankInformation userBankInformation = new UserBankInformation();

        userBankInformation.setUuid(UUID.randomUUID());
        userBankInformation.setClabe(request.clabe());
        userBankInformation.setBankName(request.bankName());
        userBankInformation.setCountry(request.country());

        return userBankInformation;
    }

    private UserBankInformationResponse toUserBankInformationResponse(UserBankInformation userBankInformation) {
        return new UserBankInformationResponse(
            userBankInformation.getUuid(),
            userBankInformation.getClabe().replaceAll("^(.{14})", "*******"),
            userBankInformation.getBankName(),
            userBankInformation.getCountry()
        );
    }
}
