package com.cb_labs.cb_flow_connect.service.impl;

import com.cb_labs.cb_flow_connect.configurations.CustomResourceLoader;
import com.cb_labs.cb_flow_connect.persistance.entities.AuthCode;
import com.cb_labs.cb_flow_connect.persistance.entities.User;
import com.cb_labs.cb_flow_connect.service.IAuthCodeService;
import com.cb_labs.cb_flow_connect.service.IAuthService;
import com.cb_labs.cb_flow_connect.service.IEmailService;
import com.cb_labs.cb_flow_connect.service.IUserService;
import com.cb_labs.cb_flow_connect.web.dto.response.AuthResponse;
import com.cb_labs.cb_flow_connect.web.dto.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private IUserService userService;

    @Autowired
    private IAuthCodeService authCodeService;

    @Autowired
    private IEmailService emailService;

    @Autowired
    private CustomResourceLoader resourceLoader;

    @Override
    public BaseResponse authUser(String email) {
        User user = userService.getOrCreate(email);
        AuthCode authCode = authCodeService.generateAuthCode(user);

        String template;
        try {
            InputStream inputStream = resourceLoader.getResource("verification_code_template.html");
            template = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        template = template.replace("INPUT-CODE", authCode.getCode());
        emailService.sendEmail(email, "CB Flow verification", template);

        return BaseResponse.builder()
                .data(toAuthResponse(user.getUuid(), user.getHasAllData()))
                .message("User retrieved")
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .code(200).build();
    }

    private AuthResponse toAuthResponse(UUID userUuid, Boolean hasAllData) {
        return new AuthResponse(userUuid, hasAllData);
    }
}
