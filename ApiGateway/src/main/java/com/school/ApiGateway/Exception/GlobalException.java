package com.school.ApiGateway.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

import java.util.HashMap;
import java.util.Map;

public class GlobalException {

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public static ResponseEntity<Map<String, Object>> internalServerError(String message) {
        Map<String, Object> successResponse = new HashMap<>();
        successResponse.put("msg", message);
        successResponse.put("status", "failure");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(successResponse);
    }


}
