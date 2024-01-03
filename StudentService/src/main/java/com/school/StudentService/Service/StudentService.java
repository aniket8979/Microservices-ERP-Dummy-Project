package com.school.StudentService.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.StudentService.DTO.StudentDTO;
import com.school.StudentService.Exception.BadRequestException;
import com.school.StudentService.Exception.GlobalExceptionHandler;
import com.school.StudentService.Exception.InternalServerException;
import com.school.StudentService.Exception.ResponseClass;
import com.school.StudentService.Model.StudentModel;
import com.school.StudentService.Repo.ClassSectionRepo;
import com.school.StudentService.Repo.StudentRepo;
import com.school.StudentService.Model.ClassSection;
import org.bouncycastle.asn1.cms.OtherRecipientInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
public class StudentService {

    @Autowired
    public StudentRepo studentRepo;

    @Autowired
    public ClassSectionRepo classSectionRepo;




    public StudentModel SaveStudent(String studentDataString, String franchiseId, String uniqueId, MultipartFile profilePic){

        // JSON serialization
        ObjectMapper jsonobj = new ObjectMapper();
        StudentDTO studentData = new StudentDTO();
        try {
            studentData = jsonobj.readValue(studentDataString, StudentDTO.class);
        } catch (JsonProcessingException e) {
            throw new BadRequestException("invalid json formatting");
        }
        StudentModel student = studentData.getStudentInfo();
        boolean stuExist = studentRepo.existsByemail(student.getEmail());
        if (stuExist) {
          throw new BadRequestException("student already exists");
        }

        // Generating UserId
        String userId;
        while(true){
            userId = UtilitiesServices.generateRecordId(uniqueId, "st");
            boolean found = studentRepo.existsByuserId(userId);
            if(!found){
                student.setUserId(userId);
                break;
            }
        }

        String section = studentData.getClassData().getSectionId();
        ClassSection classGradeData = classSectionRepo.findBysectionId(section);

        try {
            UtilitiesServices.fileSave(profilePic, UtilitiesServices.profilePicPath, student.getUserId());
        }catch (Exception e) {
            throw new InternalServerException("image cant be saved");
        }
        student.setFranchiseId(franchiseId);
        student.setDpPath(UtilitiesServices.givePath(UtilitiesServices.profilePicPath, student.getUserId()));
        student.setClassSection(classGradeData);

        studentRepo.save(student);

        return student;
    }







    public List<StudentModel> getAllStudent(String franchiseId){
        List<StudentModel> allStudents;
        allStudents = studentRepo.findAllByfranchiseId(franchiseId);
        return allStudents;
    }




}
