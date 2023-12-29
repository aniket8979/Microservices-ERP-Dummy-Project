package com.school.LoginService.Repo;

import com.school.LoginService.Model.LoginModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepo extends JpaRepository<LoginModel, Integer> {

    LoginModel getReferenceByemail(String email);
}
