package com.school.StaffService.Service;

import com.school.StaffService.Model.AllRole;
import com.school.StaffService.Model.Roles;
import com.school.StaffService.Repo.AllRoleRepo;
import com.school.StaffService.Repo.RolesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    public RolesRepo rolesRepo;

    @Autowired
    private AllRoleRepo allRoleRepo;


    public boolean saveRoleForUser(String roleId, String userId, String email, String franchiseId)
    {
        Roles addNew = new Roles();
        if(roleId.equals("USER")){
            addNew.setRoleType("USER");
            addNew.setRoleUserId(userId);
            addNew.setEmail(email);
            addNew.setFranchiseId(franchiseId);
            addNew.setRole("USER");
            addNew.setRoleId("USER");
            rolesRepo.save(addNew);
            return true;

        }else {
            AllRole thisRole = allRoleRepo.getReferenceByroleId(roleId);
            if(thisRole != null){
                addNew.setRoleType(thisRole.getRoleType());
                addNew.setRoleUserId(userId);
                addNew.setEmail(email);
                addNew.setFranchiseId(franchiseId);
                addNew.setRole(thisRole.getRoleName());
                addNew.setRoleId(thisRole.getRoleId());
                rolesRepo.save(addNew);
                return true;
            }
            return false;
        }
    }

}
