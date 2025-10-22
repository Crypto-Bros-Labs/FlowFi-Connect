package com.cb_labs.cb_flow_connect.web.controller;

import com.cb_labs.cb_flow_connect.configurations.security.user.UserAuthDto;
import com.cb_labs.cb_flow_connect.service.IUserService;
import com.cb_labs.cb_flow_connect.web.dto.request.UserRequest;
import com.cb_labs.cb_flow_connect.web.dto.response.BaseResponse;
import com.cb_labs.cb_flow_connect.web.dto.response.JwtResponse;
import com.cb_labs.cb_flow_connect.web.dto.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService service;

    @PutMapping
    public ResponseEntity<BaseResponse<UserResponse>> updateUser(@RequestBody UserRequest request) {
        return service.updateUser(request).apply();
    }

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticate the user and returns a JWT")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Login successful",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = JwtResponse.class))
            ),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid credentials")
    })
    public void login(@RequestBody UserAuthDto authDto) {
    }

    @GetMapping("/info/{userUuid}")
    public ResponseEntity<BaseResponse<UserResponse>> getUserInfo(@PathVariable UUID userUuid) {
        return service.getUserInfoByUuid(userUuid).apply();
    }
}
