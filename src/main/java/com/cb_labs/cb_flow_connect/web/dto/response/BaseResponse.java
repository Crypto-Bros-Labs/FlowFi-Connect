package com.cb_labs.cb_flow_connect.web.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter @Setter @Builder
public class BaseResponse<T> {
    private T data;
    private String message;
    private boolean success;
    private HttpStatus status;
    private int code;

    public ResponseEntity<BaseResponse<T>> apply() {
        return new ResponseEntity<>(this, this.status);
    }
}