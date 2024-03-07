package com.school.LoginService.Repo;

import com.school.LoginService.Model.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OtpRepo extends JpaRepository<Otp, Integer> {

    Otp getReferenceByemail(String email);

    void deleteByemail(String email);

    void deleteAllByemail(String email);

    List<Otp> findAllByEmail(String email);
    Otp findByEmail(String email);
}