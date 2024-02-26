package com.school.LoginService.Repo;

import com.school.LoginService.Model.FeaPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.security.Permission;
import java.util.List;

@Repository
public interface PermissionRepo  extends JpaRepository<FeaPermission,Integer > {


    FeaPermission findByFeaPerId(int permissionId);

    List<FeaPermission> findFeaPermissionByFeatures_FeatureId(int feaId);

    FeaPermission findFeaPermissionByPerName(String name);

}
