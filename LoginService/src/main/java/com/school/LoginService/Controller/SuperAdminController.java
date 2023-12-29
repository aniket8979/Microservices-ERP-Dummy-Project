package com.school.LoginService.Controller;


import com.school.LoginService.Model.SuperAdminModel;
import com.school.LoginService.Service.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class SuperAdminController {


    @Autowired
    private SuperAdminService superAdminService;

    @PostMapping("/{thename}")
    public ResponseEntity<String> micCheck(@PathVariable("thename")String thatName){
        return ResponseEntity.ok(thatName);
    }


    @PostMapping("/superadmin/register")
    public ResponseEntity<String> registerSuperAdmin(@RequestHeader("SECRET")String secret, @RequestBody SuperAdminModel superAdminInfo){
        String secretKey = "scriza987654321987654321";
        if (secretKey.equals(secret)){
            System.out.println(superAdminInfo.getAdminName());
            superAdminService.superAdminRepo.save(superAdminInfo);
            return ResponseEntity.ok("Admin Profile Created");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid SecretKey");

    }

}