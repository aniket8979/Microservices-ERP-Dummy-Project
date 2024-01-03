package com.school.StaffService.Controller;


import com.school.StaffService.Exception.ResponseClass;
import com.school.StaffService.Model.AllRole;
import com.school.StaffService.Repo.AllRoleRepo;
import com.school.StaffService.Repo.TeacherRepo;
import com.school.StaffService.Service.AllRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/role")
public class AllRoleController {

    @Autowired
    private AllRoleRepo allRoleRepo;

    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private AllRoleService allRoleService;



    @GetMapping(path = "/getall")
    public ResponseEntity<?> speceficRoles(
            @RequestHeader("roleType") String roleType,
            @RequestHeader("franchiseId") String franchiseId)
    {
        if(roleType.equals("ADMIN")){
            List<AllRole> allRoles = allRoleRepo.findAllByfranchiseId(franchiseId);
            if(!allRoles.isEmpty()){
                return ResponseClass.responseSuccess("All roles & permissions", "allRoles", allRoles);
            }
            return ResponseClass.responseFailure("Roles & Permission not found");
        }
        return ResponseClass.responseFailure("access denied");
    }


    @PostMapping("/addrole")
    public ResponseEntity<?> addRole(
            @RequestHeader("franchiseId") String franchiseId,
            @RequestHeader("roleType") String roleType,
            @RequestBody AllRole newRole)
    {
        if(roleType.equals("ADMIN")){
            List<AllRole> allRoles = allRoleRepo.findAllByfranchiseId(franchiseId);
            boolean found = allRoles.stream().anyMatch(r -> r.getRoleName().equals(newRole.getRoleName()));
            boolean foundId = allRoles.stream().anyMatch(r -> r.getRoleId().equals(newRole.getRoleId()));
            if(foundId){
                return ResponseClass.responseFailure("roleId already exists");
            }
            if(!found){
                if(newRole.getRoleName().isEmpty() || newRole.getRoleType().isEmpty()){
                    return ResponseClass.responseFailure("fields cannot be empty");
                }
                else{
                    allRoleService.roleSaved(newRole, franchiseId);
                    return ResponseClass.responseSuccess("new role saved", "roleAdded", newRole);
                }
            }
            return ResponseClass.responseFailure("role already exist");
        }
        return ResponseClass.responseFailure("access denied");
    }


    @PostMapping("/editrole")
    public ResponseEntity<?> editRole(
            @RequestBody AllRole editRole,
            @RequestHeader("franchiseId") String franchiseId,
            @RequestHeader("roleType") String roleType)
    {
        if(roleType.equals("ADMIN")){
            if(!(editRole.getRoleName().isEmpty())){
                if(!(editRole.getRoleType().isEmpty())){
                    Object edited = allRoleService.editRoleInfo(editRole, franchiseId);
                    if(edited.equals(false)){
                        return ResponseClass.responseFailure("role info not found");
                    }else {
                        return ResponseClass.responseSuccess("role info updated","RoleInfo",edited);
                    }
                }
                return ResponseClass.responseFailure("role description is empty");
            }
            return ResponseClass.responseFailure("role name is empty");
        }
        return ResponseClass.responseFailure("access denied");
    }


    @PostMapping("/deleterole")
    public ResponseEntity<?> deleteRole(
            @RequestParam("roleId") String uniqueId,
            @RequestHeader("franchiseId") String franchiseId,
            @RequestHeader("roleType") String roleType)
    {
        if(roleType.equals("ADMIN")){
            boolean deleted = allRoleService.deleteRoleInfo(uniqueId, franchiseId);
            if(deleted){
                return ResponseClass.responseSuccess("Role is deleted");
            }
            return ResponseClass.responseFailure("Role not found");
        }
        return ResponseClass.responseFailure("access denied");
    }

}