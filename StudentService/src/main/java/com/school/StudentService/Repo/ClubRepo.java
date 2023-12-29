package com.school.StudentService.Repo;

import com.school.StudentService.DTO.ClubDTO;
import com.school.StudentService.Model.ClubModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClubRepo extends JpaRepository<ClubModel, Integer> {

    List<ClubModel> findAllByfranchiseId(String franchiseId);

    ClubModel findClubById(int id);
}
