package com.school.StudentService.Repo;

import com.school.StudentService.Model.ClassGrade;
import com.school.StudentService.Model.ClassSection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassSectionRepo extends JpaRepository<ClassSection, Integer> {

    ClassSection findBysectionId(String section);

    List<ClassSection> findAllByfranchiseId(String franchiseId);

    boolean existsBysectionId(String sectionId);

    ClassSection getReferenceBysectionId(String sectionId);
}
