package com.school.StaffService.Repo;

import com.school.StaffService.Model.TeacherModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TeacherRepo extends JpaRepository<TeacherModel, Integer > {


//    @Query("SELECT a FROM TeacherModel.java a where a.userId = :userId")
    TeacherModel findByuserId(String userid);

    TeacherModel getReferenceByuserId(String userid);

    boolean existsByemail(String mailId);

    TeacherModel getReferenceByemail(String email);


    List<TeacherModel> findAllByfranchiseId(String franchiseId);

    boolean existsByuserId(String userId);
}