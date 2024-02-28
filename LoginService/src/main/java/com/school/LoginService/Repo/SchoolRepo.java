package com.school.LoginService.Repo;


import com.school.LoginService.Model.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolRepo  extends JpaRepository<School,Long> {

    School findBySchoolId(String id);

    List<School> findBySchoolNameContainingIgnoreCaseOrSchoolEmailContainingIgnoreCaseOrSchoolAddressContainingIgnoreCase(String name, String email, String address);

    School findBySchoolName(String name);
    School findBySchoolEmail(String email);

    @Query("SELECT s FROM School s JOIN FETCH s.plans")
    List<School> findAllWithPlans();






}
