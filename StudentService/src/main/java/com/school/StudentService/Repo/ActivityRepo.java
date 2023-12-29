package com.school.StudentService.Repo;

import com.school.StudentService.Model.ActivityModel;
import com.school.StudentService.Model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepo extends JpaRepository<ActivityModel, Integer> {

    ActivityModel findActivityById(int id);

    List<ActivityModel> findAllByfranchiseId(String franchiseId);

}