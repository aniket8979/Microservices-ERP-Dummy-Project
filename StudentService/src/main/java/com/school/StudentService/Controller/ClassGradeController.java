package com.school.StudentService.Controller;

import com.school.StudentService.DTO.ClassDTO;
import com.school.StudentService.Exception.ResponseClass;
import com.school.StudentService.Model.ClassGrade;
import com.school.StudentService.Service.ClassGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/class")
public class ClassGradeController {

    @Autowired
    private ClassGradeService classGradeService;


    @GetMapping("/getall")
    public ResponseEntity<?> allClassGrade(@RequestHeader("franchiseId") String franchiseId)
    {
        List<ClassDTO> classes = classGradeService.getAllClasses(franchiseId);
        return ResponseClass.responseSuccess("all class records", "classes", classes);
    }




    @PostMapping("/add")
    public ResponseEntity<?> addClassGrade(
            @RequestHeader("franchiseId") String franchiseId,
            @RequestHeader("roleType") String roleType,
            @RequestHeader("uniqueId") String uniqueId,
            @RequestBody ClassGrade classGrade){
        if(!roleType.equals("ADMIN")){
            return ResponseClass.responseFailure("access denied");
        }
        Object saved = classGradeService.addNewClass(classGrade, franchiseId,uniqueId);
        if(saved == null){
            return ResponseClass.responseFailure("name cannot be empty");
        }else if(saved.equals(false)){
            return ResponseClass.responseFailure("class with this name exists already");
        }
        return ResponseClass.responseSuccess("new class added","class",saved);
    }





    @PostMapping("/edit")
    public ResponseEntity<?> editClassGrade(
            @RequestHeader("franchiseId") String franchiseId,
            @RequestHeader("roleType") String roleType,
            @RequestHeader("uniqueId") String uniqueId,
            @RequestBody ClassGrade editClass)
    {
        if(!roleType.equals("ADMIN")){
            return ResponseClass.responseFailure("access denied");
        }
        Object edit = classGradeService.editClassGrade(editClass, franchiseId);
        if(edit == null){
            return ResponseClass.responseFailure("class does not exist");
        } else if(edit.equals(false)) {
            return ResponseClass.responseFailure("class already exists");
        } else if(edit.equals("nullClass")) {
            return ResponseClass.responseFailure("section not found");
        } else {
            return ResponseClass.responseSuccess("class updated", "class", edit);
        }

    }


    @PostMapping("/delete/{classId}")
    public ResponseEntity<?> deleteClass(
            @RequestHeader("franchiseId") String franchiseId,
            @RequestHeader("roleType") String roleType,
            @PathVariable("classId") String classId)
    {
        if(!roleType.equals("ADMIN")){
            return ResponseClass.responseFailure("access denied");
        }
        ClassGrade thisClass = classGradeService.classGradeRepo.findByclsRecordId(classId);
        if(thisClass==null){
            return ResponseClass.responseFailure("class not found");
        }
        if(!thisClass.getFranchiseId().equals(franchiseId)){
            return ResponseClass.responseFailure("class not found");
        }

        classGradeService.classGradeRepo.delete(thisClass);
        return ResponseClass.responseSuccess("class successfully deleted");
    }



}