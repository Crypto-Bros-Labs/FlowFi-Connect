package com.cb_labs.cb_flow_connect.configurations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter @Setter
@ConfigurationProperties(prefix = "capa")
public class CapaConfig {
    String baseUrl;
    String apiKey;
}
