package com.school.StudentService.Exception;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;


public class ResponseClass {

    public static ResponseEntity<Map<String, Object>> responseSuccess(String message) {
        Map<String, Object> successResponse = new HashMap<>();
        successResponse.put("msg", message);
        successResponse.put("status", "success");
        return ResponseEntity.ok(successResponse);
    }

    public static ResponseEntity<Map<String, Object>> responseSuccess(String msg, String inputType , Object inputdata) {
        Map<String, Object> successResponse = new HashMap<>();
        successResponse.put("status", "success");
        successResponse.put("msg", msg);
        successResponse.put(inputType, inputdata);
        return ResponseEntity.ok(successResponse);
    }


    public static ResponseEntity<Map<String, Object>> responseFailure(String message) {
        Map<String, Object> successResponse = new HashMap<>();
        successResponse.put("msg", message);
        successResponse.put("status", "failure");
        return ResponseEntity.ok(successResponse);
    }




//    public static ResponseEntity<Map<String, Object>> responseId(String id,String message) {
//        Map<String, Object> successResponse = new HashMap<>();
//        successResponse.put("id",id);
//        successResponse.put("message", message);
//        successResponse.put("status", "success");
//        return ResponseEntity.ok(successResponse);
//    }

//    public static ResponseEntity<Map<String, Object>> responseRole(String id,String token,String role) {
//        Map<String, Object> successResponse = new HashMap<>();
//        successResponse.put("id",id);
//        successResponse.put("token",token);
//        successResponse.put("role",role);
//        successResponse.put("success", true);
//        return ResponseEntity.ok(successResponse);
//    }





}
