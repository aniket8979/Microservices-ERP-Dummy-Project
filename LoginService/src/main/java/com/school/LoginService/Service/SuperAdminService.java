package com.school.LoginService.Service;


import com.school.LoginService.Model.Admin;
import com.school.LoginService.Repo.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuperAdminService {

    @Autowired
    public AdminRepo superAdminRepo;


    public Object createAdmin(String secret, Admin superAdminInfo) {
        String secretKey = "scriza987654321987654321";
        if (secretKey.equals(secret)) {
            System.out.println(superAdminInfo.getAdminName());
            if(!schoolIdExists(superAdminInfo.getSchoolId())){
                superAdminRepo.save(superAdminInfo);
                return "Admin Profile Created";
            }
            return false;
        }
        return null;
    }


    public boolean schoolIdExists(String uniqueId) {
        return superAdminRepo.existsBySchoolId(uniqueId);
    }
}