package com.school.LoginService.Controller;


import com.school.LoginService.DTO.SetPasswordDTO;
import com.school.LoginService.FeignService.StaffServiceFeign;
import com.school.LoginService.Model.LoginModel;
import com.school.LoginService.Repo.SuperAdminRepo;
import com.school.LoginService.Service.JwtService;
import com.school.LoginService.Service.LoginService;
import com.school.LoginService.Transient.TeacherModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.nodes.Tag;

import java.util.HashMap;
import java.util.Hashtable;

import static java.lang.StringTemplate.STR;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    public StaffServiceFeign staffServiceFeign;

    @Autowired
    private LoginService loginService;

    @Autowired
    private SuperAdminRepo superAdminRepo;

    @Autowired
    private JwtService jwtService;


    @PostMapping("/all")
    public ResponseEntity<HashMap<String, String>> loginUser(@RequestBody LoginModel loginDetail) {
        HashMap<String,String> responsebody = loginService.loginGeneratetoken(loginDetail);
        return ResponseEntity.ok(responsebody);
    }


    @PostMapping("/setpassword")
    public ResponseEntity<HashMap<String, String>> passwordChange(
            @RequestHeader("franchiseId") String email,
            @RequestBody SetPasswordDTO passdata)
    {
        HashMap<String, String> resp = new HashMap<>();
        System.out.println(STR."Request Received:\{email} ");
        System.out.println(STR."password is not received\{passdata.getPassword()} ");
        String verified = (String) loginService.updatePassword(email, passdata.getPassword(), passdata.getOtp());

        if(verified.equals("password updated")){
            resp.put("status","success");
            resp.put("msg", verified);
            return ResponseEntity.ok(resp);
        }
        resp.put("status","failed");
        resp.put("msg", verified);
        return ResponseEntity.ok(resp);
    }



    @PostMapping("/getotp")
    public ResponseEntity<?> setPassword(@RequestParam("email") String email){
        if(!email.isEmpty()){
            System.out.println(email);
            System.out.println("OTP req received");
            HashMap<String, String> updated = loginService.sendOtpAndIssueToken(email);
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.ok("email not received");
    }







}