package com.school.StudentService.Repo;

import com.school.StudentService.Model.ClassGrade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassGradeRepo extends JpaRepository<ClassGrade, Integer> {

    List<ClassGrade> findAllByfranchiseId(String franchiseId);

    boolean existsByclsRecordId(String uniqueId);

    ClassGrade findByclsRecordId(String clsRecordId);
}
