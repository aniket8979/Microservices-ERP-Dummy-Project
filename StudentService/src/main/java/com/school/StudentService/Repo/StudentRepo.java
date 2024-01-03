package com.school.StudentService.Repo;

import com.school.StudentService.Model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepo extends JpaRepository<StudentModel,Integer> {


    StudentModel getReferenceByemail(String email);

    List<StudentModel> findAllByfranchiseId(String franchiseId);

    StudentModel getReferenceByuserId(String userId);

    boolean existsByuserId(String recordUserId);

    StudentModel findByuserId(String userId);
}