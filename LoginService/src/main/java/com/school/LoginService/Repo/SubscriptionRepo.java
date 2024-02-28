package com.school.LoginService.Repo;

import com.school.LoginService.Model.SchoolSubscription;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SubscriptionRepo extends JpaRepository<SchoolSubscription,Integer> {

    SchoolSubscription findBySubsId(int id);

}
