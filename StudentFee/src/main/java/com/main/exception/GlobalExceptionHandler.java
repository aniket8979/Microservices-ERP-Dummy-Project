package com.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public  static ResponseEntity<Map<String, Object>> badRequest(String ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("error", ex);
        map.put("success", false);
        map.put("status", HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public  static ResponseEntity<Map<String, Object>> notFoundException(String ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", ex);
        errorResponse.put("success", false);
        errorResponse.put("status", HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public  static ResponseEntity<Map<String, Object>>  internalServerError(String message) {
        Map<String, Object> successResponse = new HashMap<>();
        successResponse.put("error", message);
        successResponse.put("success", false);
        successResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(successResponse);
    }


}