package com.school.LoginService.Service;

import com.school.LoginService.Exception.ResponseClass;
import com.school.LoginService.Model.FeaPermission;
import com.school.LoginService.Model.Features;
import com.school.LoginService.Repo.PermissionRepo;
import com.school.LoginService.Repo.SpecialFeaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private SpecialFeaRepo specialFeaRepo;

    @Autowired
    private PermissionRepo permissionRepo;

    public ResponseEntity<?> savePermission(int feaId, FeaPermission permission) {

        Features features = specialFeaRepo.findByFeatureId(feaId);
        if(features == null)
        {
            return ResponseClass.responseFailure("wrong feature Id");
        }
        FeaPermission permission1 = permissionRepo.findFeaPermissionByPerName(permission.getPerName());
        if(permission1!=null)
        {
            return ResponseClass.responseFailure("this permission already exits");
        }
        permission.setFeatures(features);
        permissionRepo.save(permission);
        features.getFeaPermission().add(permission);
        specialFeaRepo.save(features);

        return ResponseClass.responseSuccess("permission added successfully in Features");

    }

    public ResponseEntity<?> getAllPermission() {
        List<FeaPermission> permission = permissionRepo.findAll();
        return ResponseClass.responseSuccess("all permission","permissions",permission);
    }

    public ResponseEntity<?> getById(int permissionId) {
        FeaPermission feaPermission = permissionRepo.findByFeaPerId(permissionId);
        if(feaPermission == null)
        {
            return ResponseClass.responseFailure("wrong permission Id");
        }
        return ResponseClass.responseSuccess("permission is here","permissions",feaPermission);
    }

    public ResponseEntity<?> editById(int id, FeaPermission permission) {
        FeaPermission feaPermission = permissionRepo.findByFeaPerId(id);
        if(feaPermission == null)
        {
            return ResponseClass.responseFailure("wrong permission Id");
        }
        feaPermission.setPerName(permission.getPerName());
        feaPermission.setPerUrl(permission.getPerUrl());

        permissionRepo.save(feaPermission);
        return  ResponseClass.responseSuccess("permission updated successfully");

    }

    public ResponseEntity<?> deleteById(int permissionId) {
        FeaPermission feaPermission = permissionRepo.findByFeaPerId(permissionId);
        if(feaPermission == null)
        {
            return ResponseClass.responseFailure("wrong permission Id");
        }
        permissionRepo.deleteById(permissionId);
        return ResponseClass.responseSuccess("permission deleted successfully");
    }

    public ResponseEntity<?> getAllPerByFeaId(int featureId) {

        Features  features = specialFeaRepo.findByFeatureId(featureId);
        if(features == null)
        {
            return  ResponseClass.responseFailure("wrong feature Id");
        }

        List<FeaPermission> permission = permissionRepo.findFeaPermissionByFeatures_FeatureId(featureId);
        return  ResponseClass.responseSuccess("all permission in a features",features.getFeatureName(),permission);

    }
}
