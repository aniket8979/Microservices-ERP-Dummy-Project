package com.school.LoginService.Repo;

import com.school.LoginService.Model.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepo extends JpaRepository<Otp, Integer> {

    Otp getReferenceByemail(String email);

    void deleteByemail(String email);

    void deleteAllByemail(String email);
    List<Otp> findAllByEmail(String email);

}
