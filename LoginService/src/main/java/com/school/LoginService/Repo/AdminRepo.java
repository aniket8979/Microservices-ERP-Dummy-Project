package com.school.LoginService.Repo;


import com.school.LoginService.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin, Integer> {

    Admin getReferenceByemail(String email);

    boolean existsByuniqueId(String uniqueId);
}