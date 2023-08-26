package com.upx.ticketsapi.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.upx.ticketsapi.config.response.SuccessResponse;

public class SuccessResponseUtil {
    
    private SuccessResponseUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static <T> ResponseEntity<SuccessResponse<T>> okResponse(T data) {
        SuccessResponse<T> response = new SuccessResponse<>(HttpStatus.OK, data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public static <T> ResponseEntity<SuccessResponse<T>> createdResponse(T data) {
        SuccessResponse<T> response = new SuccessResponse<>(HttpStatus.CREATED, data);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public static <T> ResponseEntity<SuccessResponse<T>> noContentResponse() {
        SuccessResponse<T> response = new SuccessResponse<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

}
