package com.cb_labs.cb_flow_connect.service.impl;

import com.cb_labs.cb_flow_connect.persistance.entities.AuthCode;
import com.cb_labs.cb_flow_connect.persistance.entities.User;
import com.cb_labs.cb_flow_connect.persistance.respositories.IAuthCodeRepository;
import com.cb_labs.cb_flow_connect.service.IAuthCodeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class AuthCodeServiceImpl implements IAuthCodeService {

    @Autowired
    private IAuthCodeRepository repository;

    @Override
    public AuthCode generateAuthCode(User user) {
        String code;
        Random random = new Random();

        do {
            code = String.format("%04d", random.nextInt(10000));
        } while (repository.findAuthCodeByCode(code).isPresent());

        AuthCode authCode = new AuthCode();
        authCode.setCode(code);
        authCode.setCreatedAt(LocalDateTime.now());
        authCode.setExpiresAt(LocalDateTime.now().plusMinutes(15));
        authCode.setUser(user);

        return repository.save(authCode);
    }

    @Override
    public void validateAndDeleteCode(String code) {
        AuthCode authCode = repository.findAuthCodeByCode(code)
                .orElseThrow(EntityNotFoundException::new);

        if (authCode.getExpiresAt().isBefore(LocalDateTime.now())) {
            repository.delete(authCode);
            throw new RuntimeException("The code has been expired");
        }

        repository.delete(authCode);
    }
}