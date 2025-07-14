package com.cb_labs.cb_flow_connect.configurations.security;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
@Getter @Setter
@ConfigurationProperties(prefix = "juno")
public class JunoConfig {
    private String apiKey;
    private String apiSecret;
    private String baseUrl;
}
