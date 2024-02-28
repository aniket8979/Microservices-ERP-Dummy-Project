package com.school.LoginService.Service;

import com.school.LoginService.Exception.ResponseClass;
import com.school.LoginService.Model.Features;
import com.school.LoginService.Model.Plans;
import com.school.LoginService.Repo.PlansRepo;
import com.school.LoginService.Repo.SchoolRepo;
import com.school.LoginService.Repo.SpecialFeaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialFeaService
{

    @Autowired
    private SchoolRepo schoolRepo;
    
    @Autowired
    private SpecialFeaRepo specialFeaRepo;

    @Autowired
    private PlansRepo plansRepo;

        public ResponseEntity<?> saveService(int planId, Features features) {
        Plans plans = plansRepo.findPlansByPlanId(planId);
        Features features2 = specialFeaRepo.findFeaturesByFeatureName(features.getFeatureName());
        if(features2 != null)
        {
            return  ResponseClass.responseFailure("this feature already exits");
        }

        if(plans == null)
        {
            return  ResponseClass.responseFailure("wrong planId");
        }

        features.setPlans(plans);
        Features features1 = specialFeaRepo.save(features);
        plans.getFeatures().add(features1);
        plansRepo.save(plans);
        return ResponseClass.responseSuccess("Special Features Added Successfull");
        
    }


    public ResponseEntity<?> getAllService() {
        List<Features> service = specialFeaRepo.findAll();
        return ResponseClass.responseSuccess("all services","services",service);
    }

    public ResponseEntity<?> getById(int featureId) {
        Features features = specialFeaRepo.findByFeatureId(featureId);
        if(features == null)
        {
            return ResponseClass.responseFailure("wrong special id");
        }
        return ResponseClass.responseSuccess("get special feature","features",featureId);

    }

    public ResponseEntity<?> editById(int id, Features features) {

        Features service = specialFeaRepo.findByFeatureId(id);

        if(service == null)
        {
            return  ResponseClass.responseFailure("wrong service Id");
        }
        service.setFeatureName(features.getFeatureName());
        return ResponseClass.responseSuccess("service updated successfully");
    }

    public ResponseEntity<?> deleteById(int featureId) {
        Features plans1 = specialFeaRepo.findByFeatureId(featureId);

        if(plans1 == null)
        {
            return  ResponseClass.responseFailure("wrong service Id");
        }

        specialFeaRepo.delete(plans1);
        return ResponseClass.responseSuccess("service delete successfully");
    }


    public ResponseEntity<?> getAllfeaByPlanId(int planId) {

        Plans plans = plansRepo.findPlansByPlanId(planId);
        if(plans == null)
        {
            return  ResponseClass.responseFailure("wrong plan Id");
        }
        List<Features> features =  specialFeaRepo.findByPlans_PlanId(planId);
        return  ResponseClass.responseSuccess("All features",plans.getPlanName(),features);

    }
}
