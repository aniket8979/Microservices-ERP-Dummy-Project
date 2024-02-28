package com.school.LoginService.Repo;


import com.school.LoginService.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin, Long> {


    Admin findByAdminEmail(String email);

    

 //   boolean existsByuniqueId(String uniqueId);
}
