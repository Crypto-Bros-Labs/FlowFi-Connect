package com.cb_labs.cb_flow_connect.configurations.security.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
@Getter @Setter
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
    private String secretKey;
    private String tokenPrefix;
    private Integer tokenExpirationAfterDays;
    private Integer refreshTokenExpirationAfterDays;

    public Date getExpirationDate() {
        return Date.valueOf(LocalDate.now().plusDays(tokenExpirationAfterDays));
    }

    public Date getRefreshExpirationDate() {
        return Date.valueOf(LocalDate.now().plusDays(refreshTokenExpirationAfterDays));
    }
}
