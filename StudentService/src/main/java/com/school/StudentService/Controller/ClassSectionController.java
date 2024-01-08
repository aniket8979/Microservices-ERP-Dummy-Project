package com.school.StudentService.Controller;

import com.school.StudentService.DTO.ClassSectionDTO;
import com.school.StudentService.Exception.ResponseClass;
import com.school.StudentService.Model.ClassSection;
import com.school.StudentService.Service.ClassSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/section")
public class ClassSectionController {

    @Autowired
    private ClassSectionService classSectionService;

    @GetMapping("/getall")
    public ResponseEntity<?> allClassSection(@RequestHeader("franchiseId") String franchiseId)
    {
        List<ClassSectionDTO> classes = classSectionService.getAllClasses(franchiseId);
        return ResponseClass.responseSuccess("all class records", "classes", classes);
    }




    @PostMapping("/add")
    public ResponseEntity<?> addClassSection(
            @RequestHeader("franchiseId") String franchiseId,
            @RequestHeader("roleType") String roleType,
            @RequestHeader("uniqueId") String uniqueId,
            @RequestBody ClassSection classSection){
        if(!roleType.equals("ADMIN")){
            return ResponseClass.responseFailure("access denied");
        }
        Object saved = classSectionService.addNewSection(classSection, franchiseId, uniqueId);
        if(saved == null){
            return ResponseClass.responseFailure("name cannot be empty");
        }else if(saved.equals(false)){
            return ResponseClass.responseFailure("Section with this name exists already");
        }
        return ResponseClass.responseSuccess("new section added","section",saved);
    }


    @PostMapping("/edit")
    public ResponseEntity<?> editClassSection(
            @RequestHeader("franchiseId") String franchiseId,
            @RequestHeader("roleType") String roleType,
            @RequestHeader("uniqueId") String uniqueId,
            @RequestBody ClassSection editSection)
    {
        if(!roleType.equals("ADMIN")){
            return ResponseClass.responseFailure("access denied");
        }
        Object edit = classSectionService.editClassSection(editSection, franchiseId, uniqueId);
        if(edit == null){
            return ResponseClass.responseFailure("class does not exist");
        } else if(edit.equals(false)) {
            return ResponseClass.responseFailure("class already exists");
        } else if(edit.equals("nullTeacher")) {
            return ResponseClass.responseFailure("teacher not found");
        } else {
            return ResponseClass.responseSuccess("section updated", "section", edit);
        }
    }


    @PostMapping("/delete/{sectionId}")
    public ResponseEntity<?> deleteSection(
            @RequestHeader("franchiseId") String franchiseId,
            @RequestHeader("roleType") String roleType,
            @PathVariable("sectionId") String sectionId)
    {
        if(!roleType.equals("ADMIN")){
            return ResponseClass.responseFailure("access denied");
        }
        ClassSection thisSection = classSectionService.classSectionRepo.findBysectionId(sectionId);
        if(thisSection==null){
            return ResponseClass.responseFailure("section not found");
        }
        if(!thisSection.getFranchiseId().equals(franchiseId)){
            return ResponseClass.responseFailure("section not found");
        }
        classSectionService.deleteSection(thisSection);
        return ResponseClass.responseSuccess("section successfully deleted");
    }



}