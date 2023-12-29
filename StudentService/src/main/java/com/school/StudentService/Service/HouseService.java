package com.school.StudentService.Service;

import com.school.StudentService.DTO.HouseDTO;
import com.school.StudentService.Model.HouseModel;
import com.school.StudentService.Repo.HouseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HouseService {


    @Autowired
    public HouseRepo houseRepo;


    public Object editHouseInfo(HouseModel house, String franchiseId){
        HouseModel thisHouse = houseRepo.getReferenceById(house.getId());
        if(!(houseRepo.existsById(house.getId()))){
            return "notFound";
        }
        if(franchiseId.equals(thisHouse.getFranchiseId())){

            if(house.getHouseColour()!= null && !(thisHouse.getHouseColour().equals(house.getHouseColour()))) {
                thisHouse.setHouseColour(house.getHouseColour());
            }

            if(house.getHouseDescription()!= null && !(thisHouse.getHouseDescription().equals(house.getHouseDescription()))) {
                thisHouse.setHouseDescription(house.getHouseDescription());
            }
            if(house.getHouseName()!= null && !(thisHouse.getHouseName().equals(house.getHouseName()))) {
                boolean found = doesItExist(franchiseId, house.getHouseName());
                if(!found) {
                    thisHouse.setHouseName(house.getHouseName());
                }else {
                    return false;
                }
            }
            houseRepo.save(thisHouse);
            HouseDTO houseResp = new HouseDTO();
            houseResp.setId(thisHouse.getId());
            houseResp.setHouseName(thisHouse.getHouseName());
            houseResp.setHouseDescription(thisHouse.getHouseDescription());
            houseResp.setHouseColour(thisHouse.getHouseColour());
            return houseResp;
        }
        return "notFound";
    }



    public Object addNewHouse(HouseModel house, String franchiseId)
     {
        List<HouseModel> allHouses = houseRepo.findAllByfranchiseId(franchiseId);
        boolean found = allHouses.stream().anyMatch(h -> h.getHouseName().equals(house.getHouseName()));
        if(!found){
            house.setFranchiseId(franchiseId);
            houseRepo.save(house);
            return true;
        }
        return "House with this name already exists";
    }





    public boolean doesItExist(String franchiseId, String houseName){
        List<HouseModel> allHouses = houseRepo.findAllByfranchiseId(franchiseId);
        return allHouses.stream().anyMatch(h -> h.getHouseName().equals(houseName));
    }







}
