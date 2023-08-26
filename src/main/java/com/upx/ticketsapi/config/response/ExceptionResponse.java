package com.upx.ticketsapi.config.response;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResponse {
    private HttpStatus status;
    private List<String> errors;

    public ExceptionResponse(HttpStatus status, String error) {
        this.status = status;
        this.errors = List.of(error);
    }
}
