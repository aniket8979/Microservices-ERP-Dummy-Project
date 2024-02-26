package com.school.LoginService.Repo;

import com.school.LoginService.Model.Features;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecialFeaRepo extends JpaRepository<Features,Integer> {


    Features findByFeatureId(int id);

    List<Features>  findByPlans_PlanId(int planId);

    List<Features> findFeaturesBySchool_ServiceId(String schoolId);

//    List<Features> findFeaturesBy

}
