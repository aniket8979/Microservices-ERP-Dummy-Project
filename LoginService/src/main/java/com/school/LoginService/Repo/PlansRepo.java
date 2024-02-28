package com.school.LoginService.Repo;

import com.school.LoginService.Model.Plans;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlansRepo extends JpaRepository<Plans,Integer> {
    Plans findPlansByPlanId(int id);

    Plans findBySchool_SchoolId(String schoolId);
    
    @Query("SELECT DISTINCT p FROM Plans p " +
            "LEFT JOIN FETCH p.features f " +
            "WHERE LOWER(p.planName) LIKE %:keyword% " +
            "OR LOWER(f.featureName) LIKE %:keyword%")
    List<Plans> findPlansByPlanNameContainingIgnoreCaseOrFeaturesNameContainsIgnoreCase(String keyword);

    Plans findPlansByPlanName(String planName);


}
