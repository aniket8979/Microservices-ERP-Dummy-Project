package com.school.LoginService.Repo;


import com.school.LoginService.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin, Integer> {

    Admin getReferenceByAdminEmail(String email);

    boolean existsBySchoolId(String schoolId);

    boolean existsByAdminEmail(String adminEmail);
}