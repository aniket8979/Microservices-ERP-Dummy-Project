package com.school.LoginService.Service;

import com.school.LoginService.Exception.ResponseClass;
import com.school.LoginService.Model.Plans;
import com.school.LoginService.Repo.PlansRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PlanService {


    @Autowired
    private PlansRepo plansRepo;


    public ResponseEntity<?> savePlan(Plans plans) {
        Plans plans1 = plansRepo.findPlansByPlanName(plans.getPlanName());
        if(plans1 != null)
        {
            return ResponseClass.responseFailure("this plan already Exits");
        }

//        Plans plans1 = new Plans();
//        plans1.setPurchaseDate(LocalDateTime.now());
//        plans1.setPlanName(plans.getPlanName());
//        plans1.setPrice(plans.getPrice());
//        plans1.setStatus(true);
         plansRepo.save(plans);
        return ResponseClass.responseSuccess("plan added successfully");
    }

    public ResponseEntity<?> getAllPlan() {

        List<Plans> plans = plansRepo.findAll();
        return ResponseClass.responseSuccess("all plans","plans",plans);
    }

    public ResponseEntity<?> getById(int planId) {

        Plans plans = plansRepo.findPlansByPlanId(planId);
        if(plans == null)
        {
            return ResponseClass.responseFailure("wrong plans id");
        }
        return ResponseClass.responseSuccess("plans is here","plans",plans);
    }

    public ResponseEntity<?> editById(int planId,Plans plans) {

        Plans plans1 = plansRepo.findPlansByPlanId(planId);

        if(plans1 == null)
        {
            return  ResponseClass.responseFailure("wrong plan Id");
        }
        plans1.setPlanName(plans.getPlanName());
        plans1.setType(plans.getType());
        plans1.setValue(plans.getValue());
        plans1.setStudentLimit(plans.getStudentLimit());
        plans1.setPrice(plans.getPrice());
        plans1.setStatus(plans.isStatus());
        plansRepo.save(plans1);
        return ResponseClass.responseSuccess("plans updated successfully");
    }

    public ResponseEntity<?> deleteById(int planId) {

        Plans plans1 = plansRepo.findPlansByPlanId(planId);

        if(plans1 == null)
        {
            return  ResponseClass.responseFailure("wrong plan Id");
        }

        plansRepo.delete(plans1);
        return ResponseClass.responseSuccess("plans delete successfully");

    }
}
