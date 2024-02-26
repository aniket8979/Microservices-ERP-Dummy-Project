package com.school.LoginService.Service;

import com.school.LoginService.Exception.ResponseClass;
import com.school.LoginService.Model.Admin;
import com.school.LoginService.Model.Features;
import com.school.LoginService.Model.Plans;
import com.school.LoginService.Model.School;
import com.school.LoginService.Repo.AdminRepo;
import com.school.LoginService.Repo.PlansRepo;
import com.school.LoginService.Repo.SchoolRepo;
import com.school.LoginService.Repo.SpecialFeaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SchoolService
{


    @Autowired
    private SchoolRepo schoolRepo;

    @Autowired
    private SpecialFeaRepo specialFeaRepo;

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private PlansRepo plansRepo;

    public ResponseEntity<?> getAllSchool() {

        List<School> schools = schoolRepo.findAll();
        return ResponseClass.responseSuccess("all schools", "school",schools);
    }

    public ResponseEntity<?> getById(String schoolId) {
        School school = schoolRepo.findBySchoolId(schoolId);
        if(school == null)
        {
            return  ResponseClass.responseFailure("wrong school id");
        }
        return  ResponseClass.responseSuccess("schools", "school",school);
    }

    public ResponseEntity<?> deleteById(String schoolId) {
        School school = schoolRepo.findBySchoolId(schoolId);
        if(school == null)
        {
            return  ResponseClass.responseFailure("wrong school id");
        }
        schoolRepo.delete(school);
        return  ResponseClass.responseSuccess("school deleted successfully");
    }


    public ResponseEntity<?> saveSchool(String schoolName, String schoolAddress, String schoolEmail, String schoolPhone, String schoolDis, String schoolId, MultipartFile schoolImage, String adminName, String gender, String bloodGrp, String adminAddress, String adminEmail, String adminPhone, String adminPassword, MultipartFile adminImage) {

        School school = schoolRepo.findBySchoolId(schoolId);
        School school1 = new School();
        Admin admin = new Admin();
        if(school != null)
        {
            return  ResponseClass.responseFailure("this prefix already exits");
        }

        school1.setSchoolName(schoolName);
        school1.setSchoolEmail(schoolEmail);
        school1.setSchoolAddress(schoolAddress);
        school1.setSchoolPhone(schoolPhone);
        school1.setDescription(schoolDis);
        school1.setSchoolId(schoolId);
        if (!schoolImage.isEmpty()) {
            try {
                byte[] bytes = schoolImage.getBytes();
                String imagePath = "/Users/girjeshbaghel/Documents/Project/SchoolERP-main" + schoolName + "_school_image.jpg"; // Change this path to your desired directory
                FileOutputStream fos = new FileOutputStream(new File(imagePath));
                fos.write(bytes);
                fos.close();
                school1.setSchoolPhoto(imagePath);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseClass.responseFailure("Failed to store school image");
            }
        }

        schoolRepo.save(school1);
        admin.setAdminName(adminName);
        admin.setAdminEmail(adminEmail);
        admin.setAdminGender(gender);
        admin.setAdminPhone(adminPhone);
        admin.setAdminAddress(adminAddress);
        if (!adminImage.isEmpty()) {
            try {
                byte[] bytes = adminImage.getBytes();
                String imagePath1 = "/Users/girjeshbaghel/Documents/Project/SchoolERP-main" + schoolName + "_school_image.jpg"; // Change this path to your desired directory
                FileOutputStream fos = new FileOutputStream(new File(imagePath1));
                fos.write(bytes);
                fos.close();
                admin.setAdminPhoto(imagePath1);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseClass.responseFailure("Failed to store school image");
            }
        }

        adminRepo.save(admin);
        return ResponseClass.responseSuccess("school Added Successfully");

    }

    public ResponseEntity<?> editById(String id,String schoolName, String schoolAddress, String schoolPhone, Plans plansId, boolean status) {
        School school = schoolRepo.findBySchoolId(id);
        if(school != null)
        {
            return ResponseClass.responseFailure("wrong school Id");
        }
        school.setSchoolName(schoolName);
        school.setSchoolAddress(schoolAddress);
        school.setSchoolPhone(schoolPhone);
        school.setPlans(plansId);
        school.setStatus(status);
        schoolRepo.save(school);
        return ResponseClass.responseSuccess("school updated successfully");

    }

    public ResponseEntity<?> addSpeFeaSchool(String schoolId, List<Integer> specialFeaId) {

        School school = schoolRepo.findBySchoolId(schoolId);
        if(school == null)
        {
            return  ResponseClass.responseFailure("wrong school Id");
        }
        for(Integer feaId:specialFeaId)
        {
            Features features = specialFeaRepo.findByFeatureId(feaId);
            if(features == null)
            {
                return ResponseClass.responseFailure("wrong feature Id");
            }
            features.setSchool(school);
            if (!school.getFeatures().contains(features)) {
                school.getFeatures().add(features);
            }
        }
        schoolRepo.save(school);

        return ResponseClass.responseSuccess("Special features added to the school successfully");
    }

    public ResponseEntity<?> getSpeFeaSchool(String schoolId) {

        School school = schoolRepo.findBySchoolId(schoolId);
        List<Features> features = specialFeaRepo.findFeaturesBySchool_SchoolId(schoolId);

        if(school == null)
        {
            return  ResponseClass.responseFailure("wrong school Id");
        }
        return ResponseClass.responseSuccess("All Features",school.getSchoolName(),features);


    }

    public ResponseEntity<?> addPlanInSchool(String schoolId, int planId) {

        School school = schoolRepo.findBySchoolId(schoolId);
        if(school == null)
        {
            return  ResponseClass.responseFailure("wrong school Id");
        }

        Plans plans = plansRepo.findPlansByPlanId(planId);
        if(plans == null)
        {
            return  ResponseClass.responseFailure("wrong plan Id");
        }

        school.setPlans(plans);
        schoolRepo.save(school);
        plans.getSchool().add(school);
        plans.setPurchaseDate(LocalDateTime.now());
        plansRepo.save(plans);
        return ResponseClass.responseSuccess("Plan added successfully");
    }

    public ResponseEntity<?> getPerSchool(String schoolId) {

        School school = schoolRepo.findBySchoolId(schoolId);
        if(school == null)
        {
            return ResponseClass.responseFailure("wrong school Id");
        }

        Plans plans = plansRepo.findBySchool_SchoolId(schoolId);
        return  ResponseClass.responseSuccess("plans in a school",plans.getPlanName(),plans);

    }
}
