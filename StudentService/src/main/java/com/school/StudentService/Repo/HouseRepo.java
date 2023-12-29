package com.school.StudentService.Repo;

import com.school.StudentService.Model.HouseModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HouseRepo extends JpaRepository<HouseModel, Integer> {

    List<HouseModel> findAllByfranchiseId(String franchiseId);

    HouseModel findHouseById(int houseId);
}
