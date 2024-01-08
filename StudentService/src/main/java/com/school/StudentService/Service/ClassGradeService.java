package com.school.StudentService.Service;

import com.school.StudentService.DTO.ClassDTO;
import com.school.StudentService.Model.ClassGrade;
import com.school.StudentService.Model.ClassSection;
import com.school.StudentService.Repo.ClassGradeRepo;
import com.school.StudentService.Repo.ClassSectionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassGradeService {

    @Autowired
    public ClassGradeRepo classGradeRepo;

    @Autowired
    public ClassSectionRepo classSectionRepo;


    public Object addNewClass(ClassGrade classGrade, String franchiseId, String uniqueId) {
        classGrade.setFranchiseId(franchiseId);
        String classId;
        if(classGrade.getClassName() != null ){
            boolean exists = doesThisExist(franchiseId, classGrade.getClassName());
            if(!exists){

                while(true){
                    classId = UtilitiesServices.generateRecordId(uniqueId, "cls");
                    boolean found = classGradeRepo.existsByclsRecordId(classId);
                    if(!found){
                        classGrade.setClsRecordId(classId);
                        break;
                    }
                }
                classGradeRepo.save(classGrade);
                return classGrade;
            }
            return false;
        }
        return null;
    }



    public Object editClassGrade(ClassGrade editClass, String franchiseId) {
        ClassGrade thisClass = classGradeRepo.findByclsRecordId(editClass.getClsRecordId());
        if(thisClass==null){
            return null;
        }

        if(franchiseId.equals(thisClass.getFranchiseId())) {

            if(editClass.getSection().getFirst() == null && !hasSection(thisClass, editClass.getSection().getFirst().getSectionId())){

                ClassSection newSection = classSectionRepo.findBysectionId(editClass.getSection().getFirst().getSectionId());
                if(newSection != null){
                    List<ClassSection> addingSection = new ArrayList<>();
                    addingSection.add(newSection);
                    thisClass.setSection(addingSection);
                }
                return "nullClass";
            }

            if((editClass.getClassName() != null) && !(thisClass.getClassName().equals(editClass.getClassName()))) {

                boolean found = doesThisExist(franchiseId, editClass.getClassName());

                if(!found) {
                    thisClass.setClassName(editClass.getClassName());
                } else {
                    return false;
                }
            }
            classGradeRepo.save(thisClass);
            ClassDTO updatedClass = new ClassDTO();
            updatedClass.setClassId(thisClass.getClsRecordId());
            updatedClass.setClassName(thisClass.getClassName());
            return updatedClass;
        }
        return null;
    }



    public List<ClassDTO> getAllClasses(String franchiseId) {
        List<ClassGrade> allClasses = classGradeRepo.findAllByfranchiseId(franchiseId);
        List<ClassDTO> allClassDto = new ArrayList<>();
        for (ClassGrade c : allClasses){
            ClassDTO dto = new ClassDTO();
            dto.setClassId(c.getClsRecordId());
            dto.setClassName(c.getClassName());
            allClassDto.add(dto);
        }
        return allClassDto;
    }

    public boolean hasSection(ClassGrade thisClass, String editSectionId){
        return thisClass.getSection().stream().anyMatch(c -> c.getSectionId().equals(editSectionId));
    }


    public boolean doesThisExist(String franchiseId, String className){
        List<ClassGrade> allClasses = classGradeRepo.findAllByfranchiseId(franchiseId);
        return allClasses.stream().anyMatch(c -> c.getClassName().equals(className));
    }


    public ClassDTO setInDTO(ClassGrade thisClass){
        ClassDTO updatedClass = new ClassDTO();
        updatedClass.setClassId(thisClass.getClsRecordId());
        updatedClass.setClassName(thisClass.getClassName());
        return updatedClass;
    }


}
