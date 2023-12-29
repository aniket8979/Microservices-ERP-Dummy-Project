package com.school.StaffService.Service;


import com.school.StaffService.Model.AllRole;
import com.school.StaffService.Repo.AllRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AllRoleService {

    @Autowired
    private AllRoleRepo allRoleRepo;


    public void roleSaved(AllRole roleData, String franchiseId) {
        roleData.setFranchiseId(franchiseId);
        allRoleRepo.save(roleData);
    }


    public Object editRoleInfo(AllRole editRole, String franchiseId) {
        AllRole thisRole = allRoleRepo.getReferenceByroleId(editRole.getRoleId());
        if (thisRole != null) {
            if(thisRole.getFranchiseId().equals(franchiseId)){
                thisRole.setRoleName(editRole.getRoleName());
                thisRole.setRoleType(editRole.getRoleType());
                allRoleRepo.save(thisRole);
                return thisRole;
            }
            return false;
        }
        return false;
    }

    public boolean deleteRoleInfo(String roleId, String franchiseId) {
        AllRole thisRole = allRoleRepo.getReferenceByroleId(roleId);
        if(thisRole!=null){
            if(thisRole.getFranchiseId().equals(franchiseId)){
                allRoleRepo.delete(thisRole);
                return true;
            }
            return false;
        }
        return false;
    }
}
