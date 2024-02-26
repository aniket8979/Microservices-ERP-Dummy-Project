package com.school.LoginService.Repo;

import com.school.LoginService.Model.Plans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlansRepo extends JpaRepository<Plans,Integer> {
    Plans findPlansByPlanId(int id);
    Plans findBySchool_SchoolId(String schoolId);

    Plans findPlansByPlanName(String planName);


}
