package com.school.LoginService.Service;


import com.school.LoginService.Model.SuperAdminModel;
import com.school.LoginService.Repo.SuperAdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuperAdminService {

    @Autowired
    public SuperAdminRepo superAdminRepo;


    public Object createAdmin(String secret, SuperAdminModel superAdminInfo) {
        String secretKey = "scriza987654321987654321";
        if (secretKey.equals(secret)) {
            System.out.println(superAdminInfo.getAdminName());
            if(!uniqueIdExists(superAdminInfo.getUniqueId())){
                superAdminRepo.save(superAdminInfo);
                return "Admin Profile Created";
            }
            return false;
        }
        return null;
    }


    public boolean uniqueIdExists(String uniqueId) {
        return superAdminRepo.existsByuniqueId(uniqueId);
    }
}