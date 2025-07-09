package com.cb_labs.cb_flow_connect.configurations.security;


import com.cb_labs.cb_flow_connect.web.dto.response.BaseResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

@Component
public class DelegatedAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        Throwable cause = authException;
        Throwable rootCause = authException.getCause();
        while (rootCause != null && rootCause != cause) {
            cause = rootCause;
            rootCause = cause.getCause();
        }

        StringWriter sw = new StringWriter();
        cause.printStackTrace(new PrintWriter(sw));

        String errorMessage = String.format(
                "Authentication error: %s - %s",
                cause.getClass().getSimpleName(),
                cause.getMessage() != null ? cause.getMessage() : "No additional information"
        );

        BaseResponse baseResponse = BaseResponse.builder()
                .message(errorMessage)
                .status(HttpStatus.UNAUTHORIZED)
                .code(HttpServletResponse.SC_UNAUTHORIZED)
                .success(Boolean.FALSE).build();

        OutputStream responseStream = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(responseStream, baseResponse);
        responseStream.flush();

        resolver.resolveException(request, response, null, authException);
    }
}
