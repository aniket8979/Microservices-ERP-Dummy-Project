package com.school.StudentService.Controller;


import com.school.StudentService.DTO.ClubDTO;
import com.school.StudentService.Exception.ResourceNotFoundException;
import com.school.StudentService.Exception.ResponseClass;
import com.school.StudentService.Model.ClubModel;
import com.school.StudentService.Repo.ClubRepo;
import com.school.StudentService.Service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/club")
public class ClubController {


    @Autowired
    private ClubService clubService;

    @Autowired
    private ClubRepo clubRepo;



    @PostMapping("/add")
    public ResponseEntity<?> addNewClub(
            @RequestHeader("franchiseId") String franchiseId,
            @RequestHeader("roleType") String roleType,
            @RequestBody ClubModel clubData)
    {
        Map<String, Object> resp = new HashMap<>();
        if(roleType.equals("ADMIN")){
            Object saved = clubService.saveNewClub(clubData, franchiseId);
            if(saved.equals(true)) {
                resp.put("status", "success");
                resp.put("msg", "clubData added successfully");
                resp.put("clubData",clubData);
                return ResponseEntity.ok(resp);
            }
            return ResponseClass.responseFailure((String) saved);
        }
        resp.put("status", "failure");
        resp.put("msg", "permission denied");
        return ResponseEntity.ok(resp);
    }


    @GetMapping("/get")
    public ResponseEntity<List<?>> getAllClubs(
            @RequestHeader("franchiseId") String franchiseId)
    {
        List<ClubModel> clubs = clubRepo.findAllByfranchiseId(franchiseId);

        List<ClubDTO> allClubs = new ArrayList<>();
        for(ClubModel club : clubs){
            ClubDTO thisClub = new ClubDTO();
            thisClub.setId(club.getId());
            thisClub.setClubName(club.getClubName());
            thisClub.setClubDescription(club.getClubDescription());
            thisClub.setClubColour(club.getClubColour());
            thisClub.setClubIcon(club.getClubIcon());
            allClubs.add(thisClub);
        }
        return ResponseEntity.ok(allClubs);
    }


    @PostMapping("/edit")
    public ResponseEntity<?> editClubs(
            @RequestHeader("franchiseId") String franchiseId,
            @RequestHeader("roleType") String roleType,
            @RequestBody ClubModel club)
    {
        if(roleType.equals("ADMIN")){

            Object editedClub = clubService.editStuClubs(club, franchiseId);
            if(editedClub.equals(false)){
                return ResponseClass.responseFailure("already exists");
            }
            if(editedClub.equals("notFound")){
                return ResponseClass.responseFailure("club not found");
            }
            return ResponseClass.responseSuccess("club updated","club", editedClub);
        }
        return ResponseClass.responseFailure("access denied");
    }


    @PostMapping("/delete")
    public ResponseEntity<?> deleteClub(
            @RequestHeader("franchiseId") String franchiseId,
            @RequestParam("clubId") int id)
    {
        boolean deleted = clubService.deleteClub(id, franchiseId);
        if(deleted){
            return ResponseClass.responseSuccess("Club Deleted Successfully");
        }
        throw new ResourceNotFoundException("Invalid Club Info");
    }







}
