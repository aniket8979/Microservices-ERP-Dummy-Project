package com.school.LoginService.Repo;


import com.school.LoginService.Model.LoginModel;
import com.school.LoginService.Model.SuperAdminModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperAdminRepo extends JpaRepository<SuperAdminModel, Integer> {

    boolean existsByemail(String email);

    SuperAdminModel getReferenceByemail(String email);
}