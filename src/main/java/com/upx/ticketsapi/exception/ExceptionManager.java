package com.upx.ticketsapi.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.upx.ticketsapi.config.response.ExceptionResponse;

@ControllerAdvice
public class ExceptionManager extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex) {
        var apiExceptionMessage = new ExceptionResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(apiExceptionMessage,new HttpHeaders(), apiExceptionMessage.getStatus());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        var apiExceptionMessage = new ExceptionResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(apiExceptionMessage,new HttpHeaders(), apiExceptionMessage.getStatus());
    }

    @ExceptionHandler(HttpException.class) 
    public ResponseEntity<Object> handleHttpException(HttpException ex) {
        var apiExceptionMessage = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(apiExceptionMessage,new HttpHeaders(), apiExceptionMessage.getStatus());
    }
}
