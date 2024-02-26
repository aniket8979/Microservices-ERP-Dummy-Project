package com.school.LoginService.Controller;
import com.school.LoginService.Model.Plans;
import com.school.LoginService.Repo.PlansRepo;
import com.school.LoginService.Service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    private PlansRepo plansRepo;

    @Autowired
    private PlanService planService;

    @PostMapping("/addPlans")
    public ResponseEntity<?> addPlans(@RequestBody Plans plans)
    {
        return planService.savePlan(plans);

    }

    @GetMapping("/getAllPlan")
    public ResponseEntity<?> getAllPlan()
    {
        return planService.getAllPlan();

    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam int planId)
    {
        return planService.getById(planId);

    }

    @PutMapping("/editById/{id}")
    public ResponseEntity<?> editById(@PathVariable int id,@RequestBody Plans plans)
    {
        return planService.editById(id,plans);

    }



   @DeleteMapping("/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam int planId )
    {
        return planService.deleteById(planId);

    }








}
