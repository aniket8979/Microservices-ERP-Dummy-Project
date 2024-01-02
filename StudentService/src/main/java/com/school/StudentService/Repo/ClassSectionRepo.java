package com.school.StudentService.Repo;

import com.school.StudentService.Model.ClassSection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassSectionRepo extends JpaRepository<ClassSection, Integer> {

    ClassSection findBysectionId(String section);
}
