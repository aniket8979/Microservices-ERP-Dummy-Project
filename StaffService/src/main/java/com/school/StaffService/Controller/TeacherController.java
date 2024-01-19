package com.school.StaffService.Controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.StaffService.DTO.TeacherDTO;
import com.school.StaffService.Exception.BadRequestException;
import com.school.StaffService.Exception.GlobalExceptionHandler;
import com.school.StaffService.Exception.ResponseClass;
import com.school.StaffService.FeignService.ClassGradeFeign;
import com.school.StaffService.Model.Document;
import com.school.StaffService.Model.Roles;
import com.school.StaffService.Model.TeacherModel;
import com.school.StaffService.Repo.AllRoleRepo;
import com.school.StaffService.Repo.DocRepo;
import com.school.StaffService.Repo.TeacherRepo;
import com.school.StaffService.Service.Dry;
import com.school.StaffService.Service.RoleService;
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
    private RoleService roleService;

    @Autowired(required = true)
    private Dry utilities;

    @Autowired(required = true)
    private ClassGradeFeign classGradeFeign;

    @Autowired
    private AllRoleRepo allRoleRepo;

    @PostMapping(path = "/register")
    ResponseEntity<?> TeacherRegisterInput(
            @RequestHeader("franchiseId") String franchiseId,
            @RequestHeader("email") String email,
            @RequestHeader("roleType") String roleType,
            @RequestHeader("uniqueId") String uniqueId,

            @RequestParam Map<String, MultipartFile> fileMap,
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
            String userrole = data.getRoleReq();
            List<Document> userdoc = data.getDocReq();


            if (fileMap.containsKey("profilePic")){
                MultipartFile dp = fileMap.get("profilePic");
                String dppath = utilities.profilePicPath;
                String dpname = userdata.getUsername();
                utilities.fileSave(dp, dppath, dpname);
                userdata.setDpPath(dppath + "/" + dpname);
            }


            String userId;
            while(true){
                userId = utilities.generateRecordId(uniqueId, "tch");
                boolean found = teacherRepo.existsByuserId(userId);
                if(!found){
                    userdata.setUserId(userId);
                    break;
                }
            }

            boolean found = teacherRepo.existsByemail(userdata.getEmail());
            if(found){
                throw new BadRequestException("Staff with this Email Already Exist");
            }else {
                userdata.setFranchiseId(franchiseId);
            }


            if(userrole == null) {
                userrole = "USER";
            }
            boolean roleSaved = roleService.saveRoleForUser(userrole, userId, userdata.getEmail(), franchiseId);


            int itr = 0;
            Map<String, MultipartFile> files;
            files = fileMap;
            boolean documentSaved = teacherService.saveDocRegister(userdoc, files, userdata.getUserId(), franchiseId);

            if(documentSaved){

                teacherRepo.save(userdata);

                return ResponseClass.responseSuccess("staff added successfully", "staffData", data);
            }
            return ResponseClass.responseSuccess("document not saved properly", "staffData", data);
        }
        return ResponseClass.responseFailure("access denied");
    }




    @PostMapping(path = "/register1")
    ResponseEntity<?> TeacherRegister(
            @RequestHeader("franchiseId") String franchiseId,
            @RequestHeader("email") String email,
            @RequestHeader("roleType") String roleType,
            @RequestHeader("uniqueId") String uniqueId,

            @RequestParam Map<String, MultipartFile> fileMap,
            @RequestBody TeacherDTO teacherRegister)
    {

        // To only Allow Admin to register another User
        if(roleType.equals("ADMIN")){

//            System.out.println("jai shree ram");
//            ObjectMapper jsonobj = new ObjectMapper();
//
//            fileMap.remove("profilePic");
//            TeacherDTO data = null;
//            try {
//                data = jsonobj.readValue(jsondata, TeacherDTO.class);
//            } catch (JsonProcessingException e) {
//                System.out.println("Another Exception" + e);
//                throw new RuntimeException(e);
//            }

            TeacherModel userdata = teacherRegister.getUserReq() ;
            String userrole = teacherRegister.getRoleReq();
            List<Document> userdoc = teacherRegister.getDocReq();



            if (fileMap.containsKey("profilePic")){
                MultipartFile dp = fileMap.get("profilePic");
                String dppath = utilities.profilePicPath;
                String dpname = userdata.getUsername();
                utilities.fileSave(dp, dppath, dpname);
                userdata.setDpPath(dppath + "/" + dpname);
            }

            String userId;
            while(true){
                userId = utilities.generateRecordId(uniqueId, "tch");
                boolean found = teacherRepo.existsByuserId(userId);
                if(!found){
                    userdata.setUserId(userId);
                    break;
                }
            }

            boolean found = teacherRepo.existsByemail(userdata.getEmail());
            if(found){
                throw new BadRequestException("Staff with this Email Already Exist");
            }else {
                userdata.setFranchiseId(franchiseId);
            }


            if(userrole == null) {
                userrole = "USER";
            }
            boolean roleSaved = roleService.saveRoleForUser(userrole, userId, userdata.getEmail(), franchiseId);


            int itr = 0;
            Map<String, MultipartFile> files;
            files = fileMap;
            boolean documentSaved = teacherService.saveDocRegister(userdoc, files, userdata.getUserId(), franchiseId);

            if(documentSaved){

                teacherRepo.save(userdata);

                return ResponseClass.responseSuccess("staff added successfully", "staffData", teacherRegister);
            }
            return ResponseClass.responseSuccess("document not saved properly", "staffData", teacherRegister);
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
            return ResponseClass.responseSuccess("all staff data", "allStaff", allStaff);
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
            if(nn == null){
                return ResponseClass.responseFailure("user not found");
            }
            if(franchiseId.equals(nn.getFranchiseId())){
                return ResponseEntity.ok(nn);
            }
            throw new BadRequestException("invalid Id");
        }
        return ResponseClass.responseFailure("access denied");
    }


    @GetMapping("/delete/{userid}")
    public ResponseEntity<?>  deleteUser (
            @RequestHeader("franchiseId") String franchiseId,
            @RequestHeader("roleType") String roleType,
            @PathVariable("userid") String userid)
    {
        if(roleType.equals("ADMIN")) {
            TeacherModel nn = teacherService.findOneByUserId(userid);
            if(nn == null) {
                return ResponseClass.responseFailure("user not found");
            }
            teacherRepo.delete(nn);
            return ResponseClass.responseSuccess("user deleted");
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

    @GetMapping("/staff-by-userid")
    public ResponseEntity<?> staffByFound (@RequestParam("userId") String userId){
        TeacherModel staff = teacherRepo.getReferenceByuserId(userId);
        if(staff != null){
            System.out.println("from staff-service, teache Controller:"+staff.getEmail());
            return ResponseEntity.ok(staff);
        }
        return null;
    }



    @GetMapping("/checking")
    public String checking(
            @RequestHeader("franchiseId") String franchiseId,
            @RequestHeader("email") String email,
            @RequestHeader("roleType") String roleType,
            @RequestHeader("uniqueId") String uniqueId)
    {
        return franchiseId + " " + email + " " + roleType + " " + uniqueId;
    }



}