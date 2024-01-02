package com.school.StudentService.Service;

import com.school.StudentService.DTO.StudentDTO;
import com.school.StudentService.Model.StudentModel;
import com.school.StudentService.Repo.ClassSectionRepo;
import com.school.StudentService.Repo.StudentRepo;
import com.school.StudentService.Model.ClassSection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;


@Service
public class StudentService {

    @Autowired
    public StudentRepo studentRepo;

    @Autowired
    public ClassSectionRepo classSectionRepo;

    public StudentDTO SaveStudent(StudentDTO student, String franchiseId, String uniqueId){
        StudentModel studentData = student.getStudentData();

        String userId;
        while(true){
            userId = UtilitiesServices.generateRecordId(uniqueId, "st");
            boolean found = studentRepo.existsByuserId(userId);
            if(!found){
                studentData.setFranchiseId(franchiseId);
                break;
            }
        }

        studentData.setUserId(userId);

        String section = student.getClassData().getSectionId();
        ClassSection classGradeData = classSectionRepo.findBysectionId(section);

        studentData.setClassSection(classGradeData);
        studentRepo.save(studentData);
        return student;
    }

    public List<StudentModel> getAllStudent(String franchiseId){
        List<StudentModel> allStudents;
        allStudents = studentRepo.findAllByfranchiseId(franchiseId);
        return allStudents;
    }




}
