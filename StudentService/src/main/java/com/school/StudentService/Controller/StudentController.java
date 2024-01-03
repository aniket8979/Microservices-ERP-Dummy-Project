package com.school.StudentService.Controller;

import com.school.StudentService.DTO.StudentDTO;
import com.school.StudentService.Exception.ResponseClass;
import com.school.StudentService.Model.StudentModel;
import com.school.StudentService.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @RequestBody StudentDTO studentDTO)
    {
        if(roleType.equals("ADMIN")){
            System.out.println("Some error occurred");
            StudentDTO savedStudent = studentService.SaveStudent(studentDTO, franchiseId, uniqueId);
            return ResponseClass.responseSuccess("new student added", "studentForm", studentDTO);
        }
        return ResponseClass.responseFailure("access denied");
    }

    @GetMapping("/get")
    public ResponseEntity<List<StudentModel>> GetStudent(
            @RequestHeader("franchiseId") String franchiseId,
            @RequestHeader("roleType") String roleType)
    {
        System.out.println("Get All API");
        List<StudentModel> allStudent = studentService.getAllStudent(franchiseId);
        return ResponseEntity.ok(allStudent);
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


    @GetMapping("/getStudentId/{studentId}")
    public ResponseEntity<?> getStudentId(@PathVariable  String studentId)
    {
        StudentModel obj = studentService.studentRepo.findByuserId(studentId);
        if (obj != null){
            return  ResponseEntity.ok(obj);
        }
        return null;
    }

    @GetMapping("/getAllStudent/{franchiseId}")
    public ResponseEntity<?> getAllStudent(@PathVariable("franchiseId") String franchiseId)
    {
        List<StudentModel> obj = studentService.studentRepo.findAllByfranchiseId(franchiseId);
        if(obj != null){
            return  ResponseEntity.ok(obj);
        }
        return null;
    }








}
