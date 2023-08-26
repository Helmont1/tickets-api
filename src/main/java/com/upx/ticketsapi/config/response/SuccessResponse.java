package com.upx.ticketsapi.config.response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuccessResponse<T> {
    private HttpStatus status;
    private T data;

    public SuccessResponse(HttpStatus status) {
        this.status = status;
    }

}
