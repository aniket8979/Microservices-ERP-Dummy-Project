package com.school.StudentService.Service;

import com.school.StudentService.DTO.ClassSectionDTO;
import com.school.StudentService.FeignService.StaffServiceFeign;
import com.school.StudentService.Model.ClassGrade;
import com.school.StudentService.Model.ClassSection;
import com.school.StudentService.Model.StudentModel;
import com.school.StudentService.Repo.ClassGradeRepo;
import com.school.StudentService.Repo.ClassSectionRepo;
import com.school.StudentService.Transient.TeacherModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassSectionService {

    @Autowired
    public ClassSectionRepo classSectionRepo;

    @Autowired
    public StaffServiceFeign staffServiceFeign;

    @Autowired
    public ClassGradeRepo classGradeRepo;


    public Object addNewSection(ClassSection classSection, String franchiseId, String uniqueId) {
        classSection.setFranchiseId(franchiseId);
        String sectionId;
        if(classSection.getSectionName() != null ){
            boolean exists = doesThisExist(franchiseId, classSection.getSectionName());
            if(!exists){

                while(true){
                    sectionId = UtilitiesServices.generateRecordId(uniqueId, "sec");
                    boolean found = classSectionRepo.existsBysectionId(sectionId);
                    if(!found){
                        classSection.setSectionId(sectionId);
                        break;
                    }
                }
                ClassGrade grade = classGradeRepo.findByclsRecordId(classSection.getClassGrade().getClsRecordId());
                classSection.setClassGrade(grade);
                classSectionRepo.save(classSection);
                return classSection;
            }
            return false;
        }
        return null;
    }

    public Object editClassSection(ClassSection editSection, String franchiseId, String uniqueId) {
        ClassSection thisSection = classSectionRepo.findBysectionId(editSection.getSectionId());
        if(thisSection==null){
            return null;
        }

        if(franchiseId.equals(thisSection.getFranchiseId())) {

            if(editSection.getClassTeacher().getUserId() != null && !(editSection.getClassTeacher().getUserId().equals(thisSection.getClassTeacher().getUserId()))){
                // Calling feign service for data of new teacher
                TeacherModel updateTeacher = staffServiceFeign.staffByUserId(thisSection.getClassTeacher().getUserId());
                if(updateTeacher != null){
                    thisSection.setClassTeacher(updateTeacher);
                }
                return "nullTeacher";
            }

            if((editSection.getSectionName() != null) && !(thisSection.getSectionName().equals(editSection.getSectionName()))) {

                boolean found = doesThisExist(franchiseId, editSection.getSectionName());

                if(!found) {
                    thisSection.setSectionName(editSection.getSectionName());
                } else {
                    return false;
                }
            }
            classSectionRepo.save(thisSection);
            ClassSectionDTO updatedSection = new ClassSectionDTO();
            updatedSection.setSectionId(thisSection.getSectionId());
            updatedSection.setSectionName(thisSection.getSectionName());
            updatedSection.setTeacherName(thisSection.getClassTeacher().getName());
            updatedSection.setTotalStudent(thisSection.getClassStudents().size());
            return updatedSection;
        }
        return null;
    }




    public List<ClassSectionDTO> getAllClasses(String franchiseId) {
        List<ClassSection> allSection = classSectionRepo.findAllByfranchiseId(franchiseId);
        List<ClassSectionDTO> allSectionDto = new ArrayList<>();
        for (ClassSection c : allSection){
            ClassSectionDTO dto = new ClassSectionDTO();
            dto.setSectionId(c.getSectionId());
            dto.setSectionName(c.getSectionName());
//            dto.setTeacherName(c.getClassTeacher().getName());
//            dto.setTotalStudent(c.getClassStudents().size());
            allSectionDto.add(dto);
        }
        return allSectionDto;
    }


    public boolean doesThisExist(String franchiseId, String sectionName){
        List<ClassSection> allSection = classSectionRepo.findAllByfranchiseId(franchiseId);
        return allSection.stream().anyMatch(s -> s.getSectionName().equals(sectionName));
    }

    public void deleteSection(ClassSection thisSection) {
        List<StudentModel> allStudents = thisSection.getClassStudents();
        for (StudentModel student : allStudents) {
            student.setClassSection(null);
        }
        thisSection.setClassTeacher(null);
        classSectionRepo.delete(thisSection);
    }



    public ClassSectionDTO setInDTO(ClassSection thisSection){
        ClassSectionDTO updatedSection = new ClassSectionDTO();
        updatedSection.setSectionId(thisSection.getSectionId());
        updatedSection.setSectionName(thisSection.getSectionName());
        if(thisSection.getClassTeacher() != null){
            updatedSection.setTeacherName(thisSection.getClassTeacher().getName());
        }
        if(!thisSection.getClassStudents().isEmpty()){
            updatedSection.setTotalStudent(thisSection.getClassStudents().size());
        }
        return updatedSection;
    }







}

