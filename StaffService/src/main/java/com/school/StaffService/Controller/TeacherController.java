package com.school.StaffService.Controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.StaffService.DTO.TeacherDTO;
import com.school.StaffService.Exception.BadRequestException;
import com.school.StaffService.Exception.GlobalExceptionHandler;
import com.school.StaffService.Exception.ResourceNotFoundException;
import com.school.StaffService.Exception.ResponseClass;
import com.school.StaffService.FeignService.ClassGradeFeign;
import com.school.StaffService.Model.AllRole;
import com.school.StaffService.Model.Document;
import com.school.StaffService.Model.Roles;
import com.school.StaffService.Model.TeacherModel;
import com.school.StaffService.Repo.AllRoleRepo;
import com.school.StaffService.Repo.DocRepo;
import com.school.StaffService.Repo.RolesRepo;
import com.school.StaffService.Repo.TeacherRepo;
import com.school.StaffService.Service.Dry;
import com.school.StaffService.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(path = "/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private DocRepo docRepo;

    @Autowired
    private RolesRepo rolesRepo;

    @Autowired(required = true)
    private Dry handleFile;

    @Autowired(required = true)
    private ClassGradeFeign classGradeFeign;

    @Autowired
    private AllRoleRepo allRoleRepo;

    @PostMapping(path = "/register")
    ResponseEntity<?> testRequestInput(
            @RequestHeader("franchiseId") String franchiseId,
            @RequestHeader("email") String email,
            @RequestHeader("roleType") String roleType,
            @RequestParam Map<String, MultipartFile> fileMap,
            @RequestParam("profilePic") MultipartFile dp,
            @RequestParam("data") String jsondata)
    {

        // To only Allow Admin to register another User
        if(roleType.equals("ADMIN")){

            System.out.println("jai shree ram");
            ObjectMapper jsonobj = new ObjectMapper();
            fileMap.remove("profilePic");
            TeacherDTO data = null;
            try {
                data = jsonobj.readValue(jsondata, TeacherDTO.class);
            } catch (JsonProcessingException e) {
                System.out.println("Another Exception" + e);
                throw new RuntimeException(e);
            }

            TeacherModel userdata = data.getUserReq();
            TeacherDTO.RoleDTO userrole = data.getRoleReq();
            List<Document> userdoc = data.getDocReq();



            String dppath = handleFile.profilePicPath;
            MultipartFile userdp = dp;
            String dpname = userdata.getUsername();
            handleFile.fileSave(dp, dppath, dpname);
            userdata.setDpPath(dppath + "/" + dpname);

            try {
                if(!teacherRepo.existsByemail(userdata.getEmail())){
                    userdata.setFranchiseId(franchiseId);
                    teacherRepo.save(userdata);
                    System.out.println("checking if its working right");
                }else {
                    throw new BadRequestException("Staff with this Email Already Exist");
                }
            } catch (Exception e) {
                throw new BadRequestException("Error in Staff Input Data");
            }

            try {
                AllRole thisRole = allRoleRepo.getReferenceByroleId(userrole.getRoleId());
                if(thisRole != null) {
                    Roles addingRole = new Roles();
                    addingRole.setRoleUserId(userdata.getUserId());
                    addingRole.setRoleId(thisRole.getRoleId());
                    addingRole.setRole(thisRole.getRoleName());
                    addingRole.setEmail(userdata.getEmail());
                    addingRole.setRoleUserId(userdata.getUserId());
                    addingRole.setRoleType(thisRole.getRoleType());
                    rolesRepo.save(addingRole);
                }
                else {
                    throw new ResourceNotFoundException("RoleType not found");
                }
            } catch (Exception e) {
                System.out.println("Some error with role " + e);
                return GlobalExceptionHandler.internalServerError("error");
            }

            int itr = 0;
            Map<String, MultipartFile> files;
            files = fileMap;
            boolean documentSaved = teacherService.saveDocRegister(userdoc, files, userdata.getUserId(), franchiseId);
            if(documentSaved){
                return ResponseClass.responseSuccess("staff added successfully", "staffData", data);
            }
            return ResponseClass.responseSuccess("document not saved properly", "staffData", data);
        }
        return ResponseClass.responseFailure("access denied");
    }



    @GetMapping("/allstaff")
    public ResponseEntity<?> getAll(
            @RequestHeader("franchiseId") String franchiseid,
            @RequestHeader("roleType") String roleType)
    {
        if(roleType.equals("ADMIN")){
            List<TeacherModel> allStaff = teacherRepo.findAllByfranchiseId(franchiseid);
            return ResponseEntity.ok(allStaff);
        }
        return ResponseClass.responseFailure("access denied");
    }



    @GetMapping("/get/{userid}")
    public ResponseEntity<?>  getUser (
            @RequestHeader("franchiseId") String franchiseId,
            @RequestHeader("roleType") String roleType,
            @PathVariable("userid") String userid)
    {
        if(roleType.equals("ADMIN")){
            TeacherModel nn = teacherService.findOneByUserId(userid);
            if(franchiseId.equals(nn.getFranchiseId())){
                return ResponseEntity.ok(nn);
            }
            throw new BadRequestException("invalid Id");
        }
        return ResponseClass.responseFailure("access denied");
    }

    // Feign Client Service From Login Service want to know if user exists
    // I am gonna create an endpoint which return boolean if user exists


    // The /stafffound is an Feign Client service method.
    @GetMapping("/stafffound")
    public ResponseEntity<?> stafffound (@RequestParam("staffMail") String mailId){
        TeacherModel staff = teacherRepo.getReferenceByemail(mailId);
        if(staff != null){
            System.out.println("from staff-service, teache Controller:"+staff.getEmail());
            return ResponseEntity.ok(staff);
        }
        return null;
    }




}