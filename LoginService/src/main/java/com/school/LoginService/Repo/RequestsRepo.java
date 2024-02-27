package com.school.LoginService.Repo;

import com.school.LoginService.Model.RequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RequestsRepo extends JpaRepository<RequestModel, Integer> {

    RequestModel findByReqId(String reqId);
}
