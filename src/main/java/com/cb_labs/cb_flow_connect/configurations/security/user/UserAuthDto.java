package com.cb_labs.cb_flow_connect.configurations.security.user;

import lombok.Data;

@Data
public class UserAuthDto {
    private String code;
    private String email;
}
