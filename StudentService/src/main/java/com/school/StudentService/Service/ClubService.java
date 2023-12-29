package com.school.StudentService.Service;

import com.school.StudentService.DTO.ClubDTO;
import com.school.StudentService.Model.ClubModel;
import com.school.StudentService.Repo.ClubRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClubService {

    @Autowired
    public ClubRepo clubRepo;



    public Object editStuClubs(ClubModel club, String franchiseId)
    {
        ClubModel thisClub = clubRepo.findClubById(club.getId());
        if(thisClub==null){
            return "notFound";
        }

        if(franchiseId.equals(thisClub.getFranchiseId())){

            if((club.getClubDescription()!= null) && !(thisClub.getClubDescription().equals(club.getClubDescription()))){
                thisClub.setClubDescription(club.getClubDescription());
            }
            if(club.getClubColour() != null && !(club.getClubColour().equals(thisClub.getClubColour()))){
                thisClub.setClubColour(club.getClubColour());
            }
            if(club.getClubIcon() != null && !(club.getClubIcon().equals(thisClub.getClubIcon()))){
                thisClub.setClubIcon(club.getClubIcon());
            }
            if((club.getClubName() !=null) && !(thisClub.getClubName().equals(club.getClubName()))) {

                boolean found = doesThisExist(franchiseId, club.getClubName());

                if(!found){
                    thisClub.setClubName(club.getClubName());
                }else {
                    return false;
                }
            }
            clubRepo.save(thisClub);

            ClubDTO editClub = new ClubDTO();
            editClub.setId(thisClub.getId());
            editClub.setClubName(thisClub.getClubName());
            editClub.setClubDescription(thisClub.getClubDescription());
            editClub.setClubIcon(thisClub.getClubIcon());
            editClub.setClubColour(thisClub.getClubColour());
            return editClub;
        }
        return false;
    }


    public boolean deleteClub(int id, String franchiseId) {
        ClubModel getClub = clubRepo.findClubById(id);
        if(getClub != null){
            if(getClub.getFranchiseId().equals(franchiseId)){
                clubRepo.delete(getClub);
                return true;
            }
        }
        return false;
    }


    public Object saveNewClub(ClubModel clubData, String franchiseId) {
        boolean found = doesThisExist(franchiseId, clubData.getClubName());
        if(!found){
            clubData.setFranchiseId(franchiseId);
            clubRepo.save(clubData);
            return true;
        }
        return "club with this name already exists";
    }


    public boolean doesThisExist(String franchiseId, String clubName){
        List<ClubModel> allClubs = clubRepo.findAllByfranchiseId(franchiseId);
        return allClubs.stream().anyMatch(c -> c.getClubName().equals(clubName));
    }






}