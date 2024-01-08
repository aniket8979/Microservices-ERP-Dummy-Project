package com.school.StudentService.Controller;


import com.school.StudentService.DTO.CategoryDTO;
import com.school.StudentService.DTO.HouseDTO;
import com.school.StudentService.Exception.ResponseClass;
import com.school.StudentService.Model.CategoryModel;
import com.school.StudentService.Model.HouseModel;
import com.school.StudentService.Service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/house")
public class HouseController {


    @Autowired
    private HouseService houseService;


    @PostMapping("/add")
    public ResponseEntity<?> addNewHouse(
            @RequestHeader("franchiseId") String franchiseId,
            @RequestHeader("roleType") String roleType,
            @RequestHeader("uniqueId") String uniqueId,
            @RequestBody HouseModel house) {
        Map<String, Object> resp = new HashMap<>();
        if(roleType.equals("ADMIN")) {
            Object saved = houseService.addNewHouse(house, franchiseId, uniqueId);
            if(saved.equals(true)){
                return ResponseClass.responseSuccess("New House Added Successfully", "house", house);
            }
            return ResponseClass.responseFailure((String)saved);
        }
        resp.put("status", "failure");
        resp.put("msg", "permission denied");
        return ResponseEntity.ok(resp);
    }


    @PostMapping("/edit")
    public ResponseEntity<?> editHouse(
            @RequestHeader("franchiseId") String franchiseId,
            @RequestHeader("roleType") String roleType,
            @RequestBody HouseModel house)
    {
        Map<String, Object> resp = new HashMap<>();
        if(roleType.equals("ADMIN")){
            Object edited = houseService.editHouseInfo(house, franchiseId);
            if(edited.equals(false)){
                return ResponseClass.responseFailure("house already exists");
            } else if(edited.equals("notFound")){
                return ResponseClass.responseFailure("house record not found");
            }
            else {
                resp.put("status", "success");
                resp.put("msg", "House edited successfully");
                resp.put("house", edited);
                return ResponseEntity.ok(resp);
            }
        }
        resp.put("status", "failure");
        resp.put("msg", "permission denied");
        return ResponseEntity.ok(resp);
    }


    @GetMapping("/getall")
    public ResponseEntity<?> getAllHouse(
            @RequestHeader("franchiseId") String franchiseId
    )
    {
        List<HouseModel> allHouses = houseService.houseRepo.findAllByfranchiseId(franchiseId);
        return ResponseClass.responseSuccess("all houses data", "allHouses", allHouses);
    }



    @GetMapping("/get")
    public ResponseEntity<?> getHouseById(
            @RequestHeader("franchiseId") String franchiseId,
            @RequestParam("houseId") String houseId)
    {
        HouseModel house = houseService.houseRepo.findByhouseId(houseId);
        if(house == null){
            return ResponseClass.responseFailure("category not found");
        }
        HouseDTO houseDto = houseService.setInDTO(house);
        return ResponseClass.responseSuccess("category info", "category", houseDto);
    }






    @PostMapping("/delete")
    public ResponseEntity<?> deleteHouse(
            @RequestHeader("franchiseId") String franchiseId,
            @RequestHeader("roleType") String roleType,
            @RequestParam("houseId") String houseId)
    {
        if(!(roleType.equals("ADMIN"))){
            return ResponseClass.responseFailure("access denied");
        }
        HouseModel thisHouse = houseService.houseRepo.findByhouseId(houseId);
        if(thisHouse == null){
            System.out.println("null house executing");
            return ResponseClass.responseFailure("house not found");
        }
        HashMap<String, String> deleted = new HashMap<>();
        if(thisHouse.getFranchiseId().equals(franchiseId)){
            houseService.houseRepo.delete(thisHouse);
            deleted.put("status", "success");
            deleted.put("msg", "house deleted");
            return ResponseEntity.ok(deleted);
        }
        deleted.put("status", "failure");
        deleted.put("msg", "permission denied");
        return ResponseEntity.ok(deleted);
    }









}
