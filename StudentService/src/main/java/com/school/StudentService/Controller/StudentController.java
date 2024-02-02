package com.school.StudentService.Controller;

import com.school.StudentService.DTO.StudentDTO;
import com.school.StudentService.Exception.ResponseClass;
import com.school.StudentService.Model.StudentModel;
import com.school.StudentService.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping(path = "/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/register")
    public ResponseEntity<?> RegisterStudent(
            @RequestHeader("franchiseId") String franchiseId,
            @RequestHeader("roleType") String roleType,
            @RequestHeader("uniqueId") String uniqueId,
            @RequestParam(value = "profilePic", required = false) MultipartFile profilePic,
            @RequestParam("data") String studentData)
    {
        if(roleType.equals("ADMIN")){

            StudentModel savedStudent = studentService.SaveStudent(studentData, franchiseId, uniqueId, profilePic);
            return ResponseClass.responseSuccess("new student added", "studentForm", savedStudent);
        }
        return ResponseClass.responseFailure("access denied");
    }




    @GetMapping("/getall")
    public ResponseEntity<?> GetStudent(
            @RequestHeader("franchiseId") String franchiseId,
            @RequestHeader("roleType") String roleType)
    {
        System.out.println("Get All API");
        List<StudentModel> allStudent = studentService.getAllStudent(franchiseId);
        return ResponseClass.responseSuccess("all students data", "student", allStudent);
    }


    @GetMapping("/get")
    public ResponseEntity<?> getOneStudent(
            @RequestParam("userId") String studentId,
            @RequestHeader("franchiseId") String franchiseId,
            @RequestHeader("roleType") String roleType)
    {

        StudentModel obj = studentService.studentRepo.findByuserId(studentId);
        if (obj != null){
            if(!franchiseId.equals(obj.getFranchiseId())){
                return ResponseClass.responseFailure("user not found");
            }
            return  ResponseClass.responseSuccess("student information", "student", obj);
        }
        return ResponseClass.responseFailure("user not found");
    }



    @GetMapping("/delete")
    public ResponseEntity<?> deleteStudentById(
            @RequestParam("userId") String studentId,
            @RequestHeader("franchiseId") String franchiseId,
            @RequestHeader("roleType") String roleType)
    {
        if(roleType.equals("ADMIN")){
            StudentModel obj = studentService.studentRepo.findByuserId(studentId);
            if (obj != null){
                if(!franchiseId.equals(obj.getFranchiseId())){
                    return ResponseClass.responseFailure("user not found");
                }

                studentService.studentRepo.delete(obj);
                return  ResponseClass.responseSuccess("user data deleted");
            }
            return ResponseClass.responseFailure("user not found");
        }
        return ResponseClass.responseFailure("access denied");
    }


    @PostMapping("/test")
    public String testingStudent(@RequestParam("test") String testing) {
        return testing;
    }




    ////////////////////////////////////////////////////////////////
    /* These are Feign controllers. All Internal scope controllers will be created under here. */
    @PostMapping("/studentfound")
    public ResponseEntity<?> StudentFound(
            @RequestParam("email") String email)
    {
        StudentModel student = studentService.studentRepo.getReferenceByemail(email);
        if (student!= null){
            System.out.println("student StudentFound:"+ student.getEmail());
            return ResponseEntity.ok(student);
        }
        return null;
    }


    @GetMapping("/getStudentId")
    public ResponseEntity<?> getStudentId(@RequestParam("studentId") String studentId)
    {
        StudentModel obj = studentService.studentRepo.findByuserId(studentId);
//        StudentDTO
        if (obj != null){
            return  ResponseEntity.ok(obj);
        }
        return null;
    }

    @GetMapping("/getAllStudent")
    public ResponseEntity<?> getAllStudent(@RequestParam("franchiseId") String franchiseId)
    {
        List<StudentModel> obj = studentService.studentRepo.findAllByfranchiseId(franchiseId);
        if(obj != null){
            return  ResponseEntity.ok(obj);
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
