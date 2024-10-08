package com.school.StudentService.Controller;


import com.school.StudentService.DTO.ActivityDTO;
import com.school.StudentService.Exception.ResponseClass;
import com.school.StudentService.Model.ActivityModel;
import com.school.StudentService.Repo.ActivityRepo;
import com.school.StudentService.Service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/activity")
public class ActivityContoller {


    @Autowired
    private ActivityService activityService;
    
    @Autowired
    private ActivityRepo activityRepo;



    @PostMapping("/add")
    public ResponseEntity<?> addNewActivity(
            @RequestHeader("franchiseId") String franchiseId,
            @RequestHeader("roleType") String roleType,
            @RequestHeader("uniqueId") String uniqueId,
            @RequestBody ActivityModel activity) {
        Map<String, Object> resp = new HashMap<>();

        if(roleType.equals("ADMIN")) {
            Object saved = activityService.addNewActivity(activity, franchiseId, uniqueId);
            if(saved.equals(true)){
                return ResponseClass.responseSuccess("New Activity Added Successfully", "activity", activity);
            }
            return ResponseClass.responseFailure((String)saved);
        }
        return ResponseClass.responseFailure("access denied");
    }


    @PostMapping("/edit")
    public ResponseEntity<?> editHouse(
            @RequestHeader("franchiseId") String franchiseId,
            @RequestHeader("roleType") String roleType,
            @RequestBody ActivityModel activity)
    {
        Map<String, Object> resp = new HashMap<>();
        if(roleType.equals("ADMIN")){
            Object edited = activityService.editActivityInfo(activity, franchiseId);
            if(edited.equals(false)){
                return ResponseClass.responseFailure("activity already exists");
            } else if(edited.equals("notFound")){
                return ResponseClass.responseFailure("activity record not found");
            }
            else {
                resp.put("status", "success");
                resp.put("msg", "activity edited successfully");
                resp.put("house", edited);
                return ResponseEntity.ok(resp);
            }
        }
        resp.put("status", "failure");
        resp.put("msg", "permission denied");
        return ResponseEntity.ok(resp);
    }



    @GetMapping("/get")
    public ResponseEntity<?> getActivityById(@RequestParam("activityId") String activityId)
    {
        ActivityModel activity = activityRepo.findByactivityId(activityId);
        ActivityDTO activityDTO =  activityService.setActivityInDTO(activity);
        return ResponseClass.responseSuccess("activity data", "activity", activityDTO);

    }




    @GetMapping("/getall")
    public ResponseEntity<?> getAllActivity(
            @RequestHeader("franchiseId") String franchiseId
    )
    {
        List<ActivityModel> allActivities = activityService.activityRepo.findAllByfranchiseId(franchiseId);
        return ResponseClass.responseSuccess("all activities data", "allActivities", allActivities);
    }


    @PostMapping("/delete")
    public ResponseEntity<?> deleteActivity(
            @RequestHeader("franchiseId") String franchisId,
            @RequestHeader("roleType") String roleType,
            @RequestParam("activityId") String activityId)
    {
        if(!(roleType.equals("ADMIN"))){
            return ResponseClass.responseFailure("access denied");
        }
        ActivityModel thisActivity = activityRepo.findByactivityId(activityId);
        if(!(activityRepo.existsByactivityId(activityId))){
            return ResponseClass.responseFailure("activity not found");
        }
        HashMap<String, String> deleted = new HashMap<>();
        if(thisActivity.getFranchiseId().equals(franchisId)){
            activityRepo.delete(thisActivity);
            deleted.put("status", "success");
            deleted.put("msg", "activity deleted");
            return ResponseEntity.ok(deleted);
        }
        deleted.put("status", "failure");
        deleted.put("msg", "permission denied");
        return ResponseEntity.ok(deleted);
    }




}