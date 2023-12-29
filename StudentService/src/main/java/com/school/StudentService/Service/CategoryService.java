package com.school.StudentService.Service;

import com.school.StudentService.DTO.CategoryDTO;
import com.school.StudentService.Model.CategoryModel;
import com.school.StudentService.Repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryService {

    @Autowired
    public CategoryRepo categoryRepo;


    public Object editStuCategory(CategoryModel category, String franchiseId)
    {
        CategoryModel thisCategory = categoryRepo.findCategoryById(category.getId());
        if(thisCategory==null){
            return "notFound";
        }

        if(franchiseId.equals(thisCategory.getFranchiseId())){

            if((category.getCategoryDescription()!= null) && !(thisCategory.getCategoryDescription().equals(category.getCategoryDescription()))) {
                thisCategory.setCategoryDescription(category.getCategoryDescription());
            }

            if((category.getCategoryName() !=null) && !(thisCategory.getCategoryName().equals(category.getCategoryName()))) {

                boolean found = doesThisExist(franchiseId, category.getCategoryName());

                if(!found){
                    thisCategory.setCategoryName(category.getCategoryName());
                }else {
                    return false;
                }
            }
            categoryRepo.save(thisCategory);

            CategoryDTO editCategory = new CategoryDTO();
            editCategory.setId(thisCategory.getId());
            editCategory.setCategoryName(thisCategory.getCategoryName());
            editCategory.setCategoryDescription(thisCategory.getCategoryDescription());
            return editCategory;
        }
        return false;
    }


    public boolean deleteCategory(int id, String franchiseId) {
        CategoryModel getCategory = categoryRepo.findCategoryById(id);
        if(getCategory != null){
            if(getCategory.getFranchiseId().equals(franchiseId)){
                categoryRepo.delete(getCategory);
                return true;
            }
        }
        return false;
    }

    public Object saveNewCategory(CategoryModel category, String franchiseId) {
        List<CategoryModel> allCategories = categoryRepo.findAllByfranchiseId(franchiseId);
        boolean found = allCategories.stream().anyMatch(c -> c.getCategoryName().equals(category.getCategoryName()));
        if(!found){
            category.setFranchiseId(franchiseId);
            categoryRepo.save(category);
            return true;
        }
        return "category with this name already exists";
    }


    public boolean doesThisExist(String franchiseId, String categoryName){
        List<CategoryModel> allCategories = categoryRepo.findAllByfranchiseId(franchiseId);
        return allCategories.stream().anyMatch(c -> c.getCategoryName().equals(categoryName));
    }



}
