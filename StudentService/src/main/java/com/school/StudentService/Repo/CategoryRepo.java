package com.school.StudentService.Repo;

import com.school.StudentService.DTO.CategoryResp;
import com.school.StudentService.Model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<CategoryModel, Integer> {

    List<CategoryModel> findAllByfranchiseId(String franchiseId);

    CategoryModel findCategoryById(int id);

    boolean existsBycategoryId(String categoryId);

    CategoryModel findBycategoryId(String categoryId);

//    List<CategoryResp> findAllByfranchiseId(String franchiseId);
}