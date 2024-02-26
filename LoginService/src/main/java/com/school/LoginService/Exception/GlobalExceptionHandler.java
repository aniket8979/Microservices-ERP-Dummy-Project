package com.school.LoginService.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, Object>> badRequest(BadRequestException ex) {
        Map map = new HashMap();
        map.put("msg", ex.getMessage());
        map.put("status","failure");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> notFoundException(ResourceNotFoundException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("msg", ex.getMessage());
        errorResponse.put("status", "failure");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public static  ResponseEntity<Map<String, Object>>  internalServerError(String message) {
        Map<String, Object> successResponse = new HashMap<>();
        successResponse.put("msg", message);
        successResponse.put("status", "failure");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(successResponse);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleInternalServerException(Exception ex) {
        ex.printStackTrace();
        System.out.println("error : "+ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<Map<String, Object>> handleInternalServer(String message){
        Map<String, Object> successResponse = new HashMap<>();
        successResponse.put("msg", message);
        successResponse.put("status", "failure");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(successResponse);
    }

}