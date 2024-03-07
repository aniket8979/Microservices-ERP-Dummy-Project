package com.school.LoginService.Controller;


import com.school.LoginService.DTO.SetPasswordDTO;
import com.school.LoginService.Exception.ResponseClass;
import com.school.LoginService.FeignService.StaffServiceFeign;
import com.school.LoginService.Model.LoginModel;
import com.school.LoginService.Repo.AdminRepo;
import com.school.LoginService.Service.JwtService;
import com.school.LoginService.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    public StaffServiceFeign staffServiceFeign;

    @Autowired
    private LoginService loginService;

    @Autowired
    private AdminRepo superAdminRepo;

    @Autowired
    private JwtService jwtService;


    @PostMapping("/all")
    public ResponseEntity<HashMap<String, String>> loginUser(@RequestBody LoginModel loginDetail) {
        HashMap<String,String> responsebody = loginService.loginGeneratetoken(loginDetail);
        return ResponseEntity.ok(responsebody);
    }



    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyotp(
            @RequestParam("OTP") int otp,
            @RequestHeader("email") String email
    )
    {
        boolean result = loginService.verifyotp(email, otp);
        if(result){
            return ResponseClass.responseSuccess("otp verified");
        }
        return ResponseClass.responseFailure("otp failed");
    }


    @PostMapping("/setpassword")
    public ResponseEntity<HashMap<String, String>> passwordChange(
            @RequestHeader("email") String email,
            @RequestParam("password") String password)
    {
        HashMap<String, String> resp = new HashMap<>();
        System.out.println("Request Received: " +email);
        System.out.println("password is received: ");
        boolean verified = loginService.updatePassword(password, email);

        if(verified){
            resp.put("status","success");
            resp.put("msg", "Password changed");
            return ResponseEntity.ok(resp);
        }
        resp.put("status","failed");
        resp.put("msg", "password invalid");
        return ResponseEntity.ok(resp);
    }



    @PostMapping("/getotp")
    public ResponseEntity<?> setPassword(@RequestParam("email") String email)
    {
        if(!email.isEmpty()){
            System.out.println(email);
            System.out.println("OTP req received");
            HashMap<String, String> updated = loginService.sendOtpAndIssueToken(email);
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.ok("email not received");
    }


    @PostMapping("/m_verify_otp")
    public ResponseEntity<?> genPassword(
            @RequestParam("otp_mobile") int otp_mobile,
            @RequestHeader("email") String email,
            @RequestHeader("schoolId") String schoolId,
            @RequestHeader("userId") String userId,
            @RequestHeader("roleType") String roleType
            )
    {
        return loginService.generatePassword(email, otp_mobile);
    }



    @PostMapping("/m_change_password")
    public ResponseEntity<?> changePassword(
            @RequestHeader("email") String email,
            @RequestHeader("schoolId") String schoolId,
            @RequestHeader("userId") String userId,
            @RequestHeader("roleType") String roleType,
            @RequestParam("password") String password)
    {
        return loginService.changeMobilePassword(email, schoolId, userId, roleType, password);
    }






}