package com.example.mybatis.common.config.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CustomControllerAdvice {


    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> illegalArgumentExceptionHandler(IllegalArgumentException e) {
        log.error("error : {}", e.getMessage(), e);
        return new ResponseEntity<>(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> nullPointerExceptionHandler(NullPointerException e) {
        log.error("error : {}", e.getMessage(), e);
        return new ResponseEntity<>(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> generalExceptionHandler(Exception e) {
        log.error("error : {}", e.getMessage(), e);
        return new ResponseEntity<>(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
