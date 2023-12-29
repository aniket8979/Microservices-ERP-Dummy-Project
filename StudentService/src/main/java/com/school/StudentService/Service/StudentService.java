package com.school.StudentService.Service;

import com.school.StudentService.DTO.StudentDTO;
import com.school.StudentService.Model.StudentModel;
import com.school.StudentService.Repo.ClassGradeRepo;
import com.school.StudentService.Repo.StudentRepo;
import com.school.StudentService.Model.ClassGrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentService {

    @Autowired
    public StudentRepo studentRepo;

    @Autowired
    public ClassGradeRepo classGradeRepo;

    public StudentDTO SaveStudent(StudentDTO student, String franchiseId){
        StudentModel studentData = student.getStudentData();
        studentData.setFranchiseId(franchiseId);
//        System.out.println(studentData.getName());

//        student.setClassData(student.getClassData().getId());

        ClassGrade classGradeData = student.getClassData();
//        System.out.println(classGradeData.getClassName());

        studentRepo.save(studentData);
//        System.out.println(studentData.getId());
        classGradeRepo.save(classGradeData);
//        System.out.println(classGradeData.getId());
        return student;
    }

    public List<StudentModel> getAllStudent(String franchiseId){
        List<StudentModel> allStudents;
        allStudents = studentRepo.findAllByfranchiseId(franchiseId);
        return allStudents;
    }









}
