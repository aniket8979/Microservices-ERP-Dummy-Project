package com.school.StaffService.Controller;


import com.school.StaffService.Model.AllRole;
import com.school.StaffService.Model.Roles;
import com.school.StaffService.Model.TeacherModel;
import com.school.StaffService.Repo.AllRoleRepo;
import com.school.StaffService.Repo.RolesRepo;
import com.school.StaffService.Repo.TeacherRepo;
import com.school.StaffService.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private RolesRepo rolesRepo;


    @Autowired
    private RoleService roleService;

    @Autowired
    private AllRoleRepo allRoleRepo;

    @GetMapping("/getuserrole")
    public ResponseEntity<?> getUserRole(@RequestParam("email") String email){
        Roles userRole = rolesRepo.getReferenceByemail(email);
        if (userRole != null){
            return ResponseEntity.ok(userRole.getRoleType());
        }
        return null;
    }



    @PostMapping("/user/add")
    public ResponseEntity<?> addUserToRole(
            @RequestHeader("franchiseId") String franchiseId,
            @RequestHeader("roleType") String roleType,
            @RequestParam("roleUniqueId") String roleUniqueId,
            @RequestParam("userid") String userId)
    {
        if(roleType.equals("ADMIN")){

            TeacherModel staff = teacherRepo.getReferenceByuserId(userId);
            AllRole theRole = allRoleRepo.getReferenceByroleId(roleUniqueId);
            Roles addRoleToUser = new Roles();
            addRoleToUser.setRoleUserId(userId);
            addRoleToUser.setRole(theRole.getRoleName());
            addRoleToUser.setRoleId(theRole.getRoleId());
            addRoleToUser.setFranchiseId(franchiseId);
            addRoleToUser.setRoleType(theRole.getRoleType());
            addRoleToUser.setEmail(staff.getEmail());
            HashMap<String, String> resp = new HashMap<String, String>();
            resp.put("status", "success");
            resp.put("msg", "Role is assigned to user");
            return ResponseEntity.ok(resp);
        }
        return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).build();

    }


    @PostMapping("/user/edit")
    public ResponseEntity<?> editingRoles(
            @RequestHeader("franchiseId") String franchiseId,
            @RequestHeader("roleType") String roleType,
            @RequestBody Roles userrole)
    {
        HashMap<String, String> resp = new HashMap<String, String>();
        if(roleType.equals("ADMIN")){
            Roles user = rolesRepo.getReferenceByroleUserId(userrole.getRoleUserId());
            user.setEmail(userrole.getEmail());
            user.setRole(userrole.getRole());
            user.setRoleType(userrole.getRoleType());
            user.setRoleId(user.getRoleId());
            user.setFranchiseId(franchiseId);
            resp.put("status", "success");
            resp.put("msg", "Role is assigned to user");
            return ResponseEntity.ok(resp);
        }
        resp.put("status", "failure");
        resp.put("msg", "access denied");
        return ResponseEntity.ok(resp);

    }

    @PostMapping("/user/delete")
    public ResponseEntity<?> deleteUser(
            @RequestHeader("franchiseId") String franchiseId,
            @RequestHeader("roleType") String roleType,
            @RequestParam("userId") String userId)
    {
        HashMap<String, String> resp = new HashMap<String, String>();

        if(roleType.equals("ADMIN")) {
            Roles user = rolesRepo.getReferenceByroleUserId(userId);
            rolesRepo.delete(user);
            resp.put("status", "success");
            resp.put("msg", "User Deleted from role");
            return ResponseEntity.ok(resp);
        }
        resp.put("status", "failure");
        resp.put("msg", "access denied");
        return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).build();
    }



    //// Feign Controller Methods ///
    @PostMapping("/addNewRole")
    public boolean addNewRoleFeign(
            @RequestParam("email") String email,
            @RequestParam("userId") String userId,
            @RequestParam("franchiseId") String franchiseId,
            @RequestParam("roleId") String roleId)
    {
        boolean success = roleService.saveRoleForUser(roleId, userId, email, franchiseId);
        if(success){
            return true;
        }
        return false;

    }





}