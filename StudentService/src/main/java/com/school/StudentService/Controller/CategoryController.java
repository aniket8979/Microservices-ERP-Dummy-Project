package com.school.StudentService.Controller;


import com.school.StudentService.DTO.CategoryDTO;
import com.school.StudentService.Exception.ResourceNotFoundException;
import com.school.StudentService.Exception.ResponseClass;
import com.school.StudentService.Model.CategoryModel;
import com.school.StudentService.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/category")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;


    @PostMapping("/add")
    public ResponseEntity<?> addNewCategory(
            @RequestHeader("franchiseId") String franchiseId,
            @RequestHeader("roleType") String roleType,
            @RequestBody CategoryModel category)
    {
        Map<String, Object> resp = new HashMap<>();
        if(roleType.equals("ADMIN")){
            Object saved = categoryService.saveNewCategory(category, franchiseId);
            if(saved.equals(true)) {
                resp.put("status", "success");
                resp.put("msg", "category added successfully");
                resp.put("category",category);
                return ResponseEntity.ok(resp);
            }
            return ResponseClass.responseFailure((String) saved);
        }
        resp.put("status", "failure");
        resp.put("msg", "permission denied");
        return ResponseEntity.ok(resp);
    }


    @GetMapping("/get")
    public ResponseEntity<List<?>> getAllCategories(
            @RequestHeader("franchiseId") String franchiseId)
    {
        List<CategoryModel> categories = categoryService.categoryRepo.findAllByfranchiseId(franchiseId);

        List<CategoryDTO> allCategories = new ArrayList<>();
        for(CategoryModel category : categories){
            CategoryDTO thisCategory = new CategoryDTO();
            thisCategory.setId(category.getId());
            thisCategory.setCategoryName(category.getCategoryName());
            thisCategory.setCategoryDescription(category.getCategoryDescription());
            allCategories.add(thisCategory);
        }
        return ResponseEntity.ok(allCategories);
    }


    @PostMapping("/edit")
    public ResponseEntity<?> editCategory(
            @RequestHeader("franchiseId") String franchiseId,
            @RequestHeader("roleType") String roleType,
            @RequestBody CategoryModel category)
    {
        if(roleType.equals("ADMIN")){

            Object editedCategory = categoryService.editStuCategory(category, franchiseId);
            if(editedCategory.equals(false)){
                return ResponseClass.responseFailure("already exists");
            }
            if(editedCategory.equals("notFound")){
                return ResponseClass.responseFailure("category not found");
            }
            return ResponseClass.responseSuccess("category updated","category",editedCategory);
        }
        return ResponseClass.responseFailure("access denied");
    }


    @PostMapping("/delete")
    public ResponseEntity<?> deleteCategory(
            @RequestHeader("franchiseId") String franchiseId,
            @RequestParam("categoryId") int id)
    {
        boolean deleted = categoryService.deleteCategory(id, franchiseId);
        if(deleted){
            return ResponseClass.responseSuccess("category deleted successfully");
        }
        throw new ResourceNotFoundException("invalid category");
    }






}