package com.school.StudentService.Service;

import com.school.StudentService.DTO.ActivityDTO;
import com.school.StudentService.Model.ActivityModel;
import com.school.StudentService.Repo.ActivityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ActivityService {

    @Autowired
    public ActivityRepo activityRepo;

    public Object editActivityInfo(ActivityModel activity, String franchiseId)
    {
        ActivityModel thisActivity = activityRepo.findActivityById(activity.getId());
        if(thisActivity == null){
            return "notFound";
        }
        if(thisActivity.getFranchiseId().equals(franchiseId)){
            if(activity.getDescription() != null && !(activity.getDescription().equals(thisActivity.getDescription()))){
                thisActivity.setDescription(activity.getDescription());
            }
            if(activity.getActivityColour() != null && !(activity.getActivityColour().equals(thisActivity.getActivityColour()))){
                thisActivity.setActivityColour(activity.getActivityColour());
            }
            if(activity.getActivityIcon() != null && !(activity.getActivityIcon().equals(thisActivity.getActivityIcon()))){
                thisActivity.setActivityIcon(activity.getActivityIcon());
            }
            if(activity.getActivityDate() != null && !(activity.getActivityDate().equals(thisActivity.getActivityDate()))){
                thisActivity.setActivityDate(activity.getActivityDate());
            }
            if(activity.getActivityName() != null && !(activity.getActivityName().equals(thisActivity.getActivityName()))){
                boolean found = doesItExist(franchiseId, activity.getActivityName());
                if(!found) {
                    thisActivity.setActivityName(activity.getActivityName());
                }else {
                    return false;
                }
            }
            activityRepo.save(thisActivity);
            ActivityDTO activityDTO = new ActivityDTO();
            activityDTO.setActivityName(thisActivity.getActivityName());
            activityDTO.setActivityColour(thisActivity.getActivityColour());
            activityDTO.setActivityDate(thisActivity.getActivityDate());
            activityDTO.setDescription(thisActivity.getDescription());
            activityDTO.setActivityIcon(thisActivity.getActivityIcon());
            return activityDTO;
        }
        return "notFound";
    }



    public Object addNewActivity(ActivityModel activity, String franchiseId)
    {
        boolean found = doesItExist(franchiseId, activity.getActivityName());
        if(!found){
            activity.setFranchiseId(franchiseId);
            activityRepo.save(activity);
            return true;
        }
        return "Activity with this name already exists";
    }





    public boolean doesItExist(String franchiseId, String activityName){
        List<ActivityModel> allActivities = activityRepo.findAllByfranchiseId(franchiseId);
        return allActivities.stream().anyMatch(a -> a.getActivityName().equals(activityName));
    }




}
