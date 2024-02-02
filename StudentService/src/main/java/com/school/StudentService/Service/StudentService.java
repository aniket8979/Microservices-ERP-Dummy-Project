package com.school.StudentService.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.StudentService.DTO.StudentDTO;
import com.school.StudentService.Exception.BadRequestException;
import com.school.StudentService.Exception.EventAlreadyExists;
import com.school.StudentService.Exception.GlobalExceptionHandler;
import com.school.StudentService.Exception.InternalServerException;
import com.school.StudentService.FeignService.StaffServiceFeign;
import com.school.StudentService.Model.StudentModel;
import com.school.StudentService.Repo.ClassSectionRepo;
import com.school.StudentService.Repo.StudentRepo;
import com.school.StudentService.Model.ClassSection;
import org.hibernate.id.CompositeNestedGeneratedValueGenerator;
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

    @Autowired
    public StaffServiceFeign staffServiceFeign;




    public StudentModel SaveStudent(String studentDataString, String franchiseId, String uniqueId, MultipartFile profilePic){

        // JSON serialization & processing
        ObjectMapper jsonobj = new ObjectMapper();
        StudentDTO studentData = new StudentDTO();
        try {
            studentData = jsonobj.readValue(studentDataString, StudentDTO.class);
        } catch (JsonProcessingException e) {
            System.out.println(e);
            throw new BadRequestException("invalid json formatting");
        }


        // Saving data related to student registration
        StudentModel student = studentData.getStudentInfo();
        boolean stuExist = studentRepo.existsByemail(student.getEmail());
        if (stuExist) {
          throw new EventAlreadyExists("student email already exists");
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

        // Saving data related to Students Class and Section Information.
        String section = studentData.getClassData().getSectionId();

        ClassSection classSectionData = classSectionRepo.findBysectionId(section);


        // Setting Students Role related information.
        String role = studentData.getRoleInfo();
        String roleId;
        if(role == null) {
            roleId = "USER";
            staffServiceFeign.saveStudentInRole(student.getEmail(), student.getUserId(), franchiseId, roleId);

        }else {

            staffServiceFeign.saveStudentInRole(student.getEmail(), student.getUserId(), franchiseId, role);
        }


        // Saving profile picture for student and setting its path.
        if(!profilePic.isEmpty()){
            System.out.println("Saving profile picture for student");

            try {
                UtilitiesServices.fileSave(profilePic, UtilitiesServices.profilePicPath, student.getUserId());

                student.setDpPath(UtilitiesServices.givePath(UtilitiesServices.profilePicPath, student.getUserId()));

            }catch (Exception e) {
                throw new InternalServerException("image cant be saved");
            }
        }

        student.setFranchiseId(franchiseId);
        student.setClassSection(classSectionData);

        studentRepo.save(student);

        return student;
    }







    public List<StudentModel> getAllStudent(String franchiseId){
        List<StudentModel> allStudents;
        allStudents = studentRepo.findAllByfranchiseId(franchiseId);
        return allStudents;
    }




}
