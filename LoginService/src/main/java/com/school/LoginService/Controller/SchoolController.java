package com.school.LoginService.Controller;

import com.school.LoginService.Model.Plans;
import com.school.LoginService.Service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/sch")
@RestController
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @PostMapping("/addSchool")
    public ResponseEntity<?> addSchool(
            @RequestParam String schoolName,
            @RequestParam String schoolAddress,
            @RequestParam String schoolEmail,
            @RequestParam String schoolPhone,
            @RequestParam String schoolDis,
            @RequestParam String schoolId,
            @RequestParam(value = "schoolImage", required = false) MultipartFile schoolImage,
            @RequestParam String adminName,
            @RequestParam String gender,
            @RequestParam String adminAddress,
            @RequestParam String adminEmail,
            @RequestParam String adminPhone,
            @RequestParam(value = "adminImage", required = false) MultipartFile adminImage
    )
    {
        return schoolService.saveSchool(schoolName,schoolAddress,schoolEmail,schoolPhone,schoolDis,schoolId,schoolImage,adminName,gender,adminAddress,adminEmail, adminPhone,adminImage);

    }
    @GetMapping("/getAllSchool")
    public ResponseEntity<?> getAllSchool()
    {
        return schoolService.getAllSchool();

    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam String schoolId)
    {
        return schoolService.getById(schoolId);

    }

    @PutMapping("/editById/{id}")
    public ResponseEntity<?> editById(@PathVariable String id,@RequestParam String schoolName,@RequestParam String schoolAddress,@RequestParam String schoolPhone, @RequestParam Plans plansId,@RequestParam boolean status )
    {
        return schoolService.editById(id,schoolName,schoolAddress,schoolPhone,plansId,status);

    }



    @DeleteMapping("/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam String schoolId )
    {
        return schoolService.deleteById(schoolId);

    }

    @PostMapping("/addFeaBySchId/{schoolId}")
    public ResponseEntity<?> addSpeFeaSchool(@PathVariable String schoolId, @RequestParam List<Integer> featureId)
    {
        return schoolService.addSpeFeaSchool(schoolId,featureId);

    }

    @PostMapping("/addPlanInSchool/{schoolId}")
    public ResponseEntity<?> addPlanSchool(@PathVariable String schoolId, @RequestParam int planId)
    {
        return schoolService.addPlanInSchool(schoolId,planId);

    }

    @GetMapping("/getPerBySchId/{schoolId}")
    public ResponseEntity<?> getPerBySchool(@RequestParam String schoolId)
    {
        return schoolService.getPerSchool(schoolId);

    }
    @GetMapping("/getFeaBySchId/{schoolId}")
    public ResponseEntity<?> getSpeFeaSchool(@RequestParam String schoolId)
    {
        return schoolService.getSpeFeaSchool(schoolId);

    }







}
