package com.school.LoginService.Repo;


import com.school.LoginService.Model.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepo  extends JpaRepository<School,Long> {

    School findByServiceId(String id);

    School findBySchoolName(String name);





}
