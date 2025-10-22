package com.cb_labs.cb_flow_connect.configurations.security.filters;

import com.cb_labs.cb_flow_connect.configurations.security.jwt.JwtConfig;
import com.cb_labs.cb_flow_connect.configurations.security.user.UserAuthDto;
import com.cb_labs.cb_flow_connect.configurations.security.user.UserDetailsImpl;
import com.cb_labs.cb_flow_connect.web.dto.response.BaseResponse;
import com.cb_labs.cb_flow_connect.web.dto.response.JwtResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.sql.Date;

@AllArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        UserAuthDto userAuthDto;

        try {
            userAuthDto = new ObjectMapper().readValue(request.getReader(), UserAuthDto.class);
        } catch (IOException exception) {
            throw new RuntimeException(exception.getLocalizedMessage());
        }

        UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
                userAuthDto.getEmail(),
                userAuthDto.getCode()
        );

        return getAuthenticationManager().authenticate(usernamePAT);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();

        Date expirationDate = jwtConfig.getExpirationDate();
        String token = Jwts.builder()
                .setSubject(userDetails.getName())
                .claim("authorities", userDetails.getAuthorities())
                .claim("username", userDetails.getName())
                .setIssuedAt(new java.util.Date())
                .setExpiration(expirationDate)
                .signWith(secretKey).compact();

        Date refreshTokenExpirationDate = jwtConfig.getRefreshExpirationDate();
        String refreshToken = Jwts.builder()
                .setSubject(userDetails.getName())
                .setIssuedAt(new java.util.Date())
                .setExpiration(refreshTokenExpirationDate)
                .signWith(secretKey).compact();

        response.addHeader("Authorization", jwtConfig.getTokenPrefix() + token);

        JwtResponse jwtResponse = new JwtResponse(token, refreshToken);
        BaseResponse baseResponse = BaseResponse.builder()
                .data(jwtResponse)
                .message("Successfully authenticated")
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .code(200).build();

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(new ObjectMapper().writeValueAsString(baseResponse));
        response.getWriter().flush();

        super.successfulAuthentication(request, response, chain, authResult);
    }
}
