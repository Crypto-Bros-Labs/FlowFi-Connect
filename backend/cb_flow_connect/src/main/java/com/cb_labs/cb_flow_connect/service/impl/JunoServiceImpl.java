package com.cb_labs.cb_flow_connect.service.impl;

import com.cb_labs.cb_flow_connect.configurations.security.JunoConfig;
import com.cb_labs.cb_flow_connect.service.IJunoService;
import com.cb_labs.cb_flow_connect.web.dto.models.juno.response.JunoClabeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;


@Service
public class JunoServiceImpl implements IJunoService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JunoConfig junoConfig;

    @Override
    public JunoClabeResponse createClabe() {
        String endpoint = "/mint_platform/v1/clabes";
        String path = junoConfig.getBaseUrl() + endpoint;
        String httpMethod = "POST";

        long nonce = System.currentTimeMillis() / 1000 * 1000;

        String signature = generateSignature(nonce, httpMethod, endpoint, "{}");
        String authHeader = String.format("Bitso %s:%d:%s", junoConfig.getApiKey(), nonce, signature);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", authHeader);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(Map.of(), headers);

        try {
            ResponseEntity<JunoClabeResponse> response = restTemplate.exchange(
                    path,
                    HttpMethod.POST,
                    entity,
                    JunoClabeResponse.class
            );

            return response.getBody();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getLocalizedMessage());
        }
    }

    private String generateSignature(long nonce, String httpMethod, String requestPath, String jsonPayload) {
        try {
            String message = nonce + httpMethod + requestPath + jsonPayload;

            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(
                    junoConfig.getApiSecret().getBytes(StandardCharsets.UTF_8),
                    "HmacSHA256"
            );
            mac.init(secretKeySpec);

            byte[] hash = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);

                if (hex.length() == 1) {
                    hexString.append('0');
                }

                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
