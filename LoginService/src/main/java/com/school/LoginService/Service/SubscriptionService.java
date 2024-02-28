package com.school.LoginService.Service;

import com.school.LoginService.Exception.ResponseClass;
import com.school.LoginService.Model.Features;
import com.school.LoginService.Model.School;
import com.school.LoginService.Model.SchoolSubscription;
import com.school.LoginService.Repo.SchoolRepo;
import com.school.LoginService.Repo.SubscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepo subscriptionRepo;

    @Autowired
    private SchoolRepo schoolRepo;

    public ResponseEntity<?> getAllSubs() {

        List<SchoolSubscription> schoolSubscriptions =  subscriptionRepo.findAll();
        return ResponseClass.responseSuccess("all subscription","subscription",schoolSubscriptions);
    }


    public ResponseEntity<?> editSubsById(int id, Double price, int planId, String phone, boolean status) {

        SchoolSubscription schoolSubscription = subscriptionRepo.findBySubsId(id);
        if(schoolSubscription == null)
        {
            return  ResponseClass.responseFailure("wrong subscription Id");
        }

        schoolSubscription.setPrice(price);
        schoolSubscription.setPlan(planId);
        schoolSubscription.setPhoneNo(phone);
        schoolSubscription.setStatus(status);
        subscriptionRepo.save(schoolSubscription);

        School school = schoolRepo.findBySchoolId(schoolSubscription.getSchoolId());
        school.setSchoolPhoto(phone);
        schoolRepo.save(school);

        return ResponseClass.responseSuccess("subscription updated successfully");
    }

    public ResponseEntity<?> deleteSubsById(int id) {

        SchoolSubscription schoolSubscription = subscriptionRepo.findBySubsId(id);
        if(schoolSubscription == null)
        {
            return  ResponseClass.responseFailure("wrong subscription Id");
        }
        subscriptionRepo.delete(schoolSubscription);
        return ResponseClass.responseSuccess("subscription delete successfully");
    }

    public ResponseEntity<?> getById(int id) {
        SchoolSubscription schoolSubscription = subscriptionRepo.findBySubsId(id);
        if(schoolSubscription == null)
        {
            return  ResponseClass.responseFailure("wrong subscription Id");
        }
        return ResponseClass.responseSuccess("subscription","subscription",schoolSubscription);
    }
}
