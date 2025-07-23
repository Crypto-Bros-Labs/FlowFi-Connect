package com.cb_labs.cb_flow_connect.service.impl;

import com.cb_labs.cb_flow_connect.persistance.entities.User;
import com.cb_labs.cb_flow_connect.persistance.respositories.IUserRepository;
import com.cb_labs.cb_flow_connect.service.IRoleService;
import com.cb_labs.cb_flow_connect.service.IUserService;
import com.cb_labs.cb_flow_connect.web.dto.request.UserRequest;
import com.cb_labs.cb_flow_connect.web.dto.response.BaseResponse;
import com.cb_labs.cb_flow_connect.web.dto.response.UserResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository repository;

    @Autowired
    private IRoleService roleService;

    @Override
    public User getUserByUuid(UUID uuid) {
        return repository.findByUuid(uuid).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public User getUserByValidAuthCode(String authCode) {
        return repository.findUserByValidAuthCode(authCode)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public User getOrCreate(String email) {
        return repository.findByEmail(email)
                .orElseGet(() -> repository.save(newUser(email)));
    }

    @Override
    public User updateUser(User user) {
        return repository.save(user);
    }

    @Override
    public BaseResponse updateUser(UserRequest request) {
        User user = getUserByUuid(request.userUuid());
        updateUser(user, request);

        return BaseResponse.builder()
                .data(toUserResponse(user))
                .message("User updated")
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .code(200).build();
    }

    private void updateUser(User user, UserRequest request) {
        user.setPhone(request.phone());
        user.setFullName(request.fullName());
        user.setHasAllData(Boolean.TRUE);
    }

    private User newUser(String email) {
        User user = new User();

        user.setUuid(UUID.randomUUID());
        user.setEmail(email);
        user.setPhone("");
        user.setFullName("");
        user.setIsNonBlocked(Boolean.TRUE);
        user.setHasAllData(Boolean.FALSE);
        user.setRole(roleService.getRoleById(1));

        return user;
    }

    private UserResponse toUserResponse(User user) {
        return new UserResponse(
            user.getUuid(),
            user.getPhone(),
            user.getFullName(),
            user.getEmail()
        );
    }
}
