package com.school.StudentService.Controller;

import com.school.StudentService.Model.ClassGrade;
import com.school.StudentService.Repo.ClassGradeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/class")
public class ClassGradeController {

    @Autowired
    private ClassGradeRepo classGradeRepo;

    public ResponseEntity<ClassGrade> addClassGrade(ClassGrade classGrade){
        classGradeRepo.save(classGrade);
        return ResponseEntity.ok(classGrade);
    }




}
