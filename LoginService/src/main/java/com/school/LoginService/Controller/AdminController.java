package com.school.LoginService.Controller;


import com.school.LoginService.Exception.ResponseClass;
import com.school.LoginService.Model.Admin;
import com.school.LoginService.Service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private AdminService superAdminService;

    @PostMapping("/{thename}")
    public ResponseEntity<String> micCheck(@PathVariable("thename")String thatName){
        return ResponseEntity.ok(thatName);
    }


    @PostMapping("/superadmin/register")
    public ResponseEntity<?> registerSuperAdmin(
            @RequestHeader("SECRET")String secret,
            @RequestParam String adminId,
            @RequestParam String adminName,
            @RequestParam String adminGender,
            @RequestParam String adminAddress,
            @RequestParam String adminEmail,
            @RequestParam String adminPhone,
            @RequestParam String adminPassword
    )
    {
        boolean admin = superAdminService.createAdmin(secret, adminId, adminName, adminGender, adminAddress, adminEmail, adminPhone, adminPassword);
        if(admin){

            return ResponseClass.responseSuccess("admin created successfully");
        }
        return ResponseClass.responseFailure("admin creation failed");
    }

//    @PostMapping(value = "/record-status/{recordId}")
//    public ResponseEntity<Object> recordIdStatus(@PathVariable("recordId") String recordId){
//        boolean isExist =
//    }

}