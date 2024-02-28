package com.school.LoginService.Service;

import com.school.LoginService.Exception.BadRequestException;
import com.school.LoginService.Model.Admin;
import com.school.LoginService.Model.LoginModel;
import com.school.LoginService.Repo.AdminRepo;
import com.school.LoginService.Repo.LoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    public AdminRepo adminRepo;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    public LoginRepo loginRepo;




    public boolean createAdmin(String secret, String adminId, String adminName, String adminGender, String adminAddress, String adminEmail, String adminPhone, String adminPassword)
    {
        String secretKey = "scriza987654321987654321";
        if(secretKey.equals(secret)){
            Admin adminExist = adminRepo.findByAdminEmail(adminEmail);
            if(adminExist != null){
                throw new BadRequestException("admin already exists");
            }
            LoginModel adminLoginExist = loginRepo.findByemail(adminEmail);
            if(adminLoginExist != null){
                throw new BadRequestException("admin already exists");
            }
            try {
                Admin admin = new Admin();
                admin.setSchoolId(adminId);
                admin.setAdminEmail(adminEmail);
                admin.setAdminAddress(adminAddress);
                admin.setAdminGender(adminGender);
                admin.setAdminName(adminName);
                admin.setAdminPhone(adminPhone);
                adminRepo.save(admin);
            }catch (Exception e) {
                System.out.println("exception at admin table "+e.getMessage());
                throw new BadRequestException("unable to save admin");
            }

            try{
                LoginModel adminLogin = new LoginModel();
                adminLogin.setRole("ADMIN");
                adminLogin.setEmail(adminEmail);
                adminLogin.setUserId(String.valueOf(adminLogin.getId()));
                adminLogin.setSchoolId(adminId);
                adminLogin.setPassword(passwordEncoder.encode(adminPassword));
                loginRepo.save(adminLogin);

            }catch (Exception e) {
                System.out.println("exception at login table "+e.getMessage());

                throw new BadRequestException("unable save admin login details");
            }
            return true;
        }
        throw new BadRequestException("Invalid Secret Key Header");

    }
//
//    public Object createAdmin(String secret, Admin superAdminInfo) {
//        return  null;
//    }
}
