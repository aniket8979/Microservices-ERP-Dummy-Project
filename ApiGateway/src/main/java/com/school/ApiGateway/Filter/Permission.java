package com.school.ApiGateway.Filter;

import com.school.ApiGateway.Feign.RolePermissionsData;
import com.school.ApiGateway.Utils.RolePermissionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;


public class Permission {

    @Autowired
    private RolePermissionsData rolePermissionsData;

    // This contains the role & permissions data for all logged in users on the server in runtime memory
    ConcurrentHashMap<String, HashMap<String, ArrayList<String>>> userRoleData = new ConcurrentHashMap<>();

    public void permissionsFlow(String userId){

        if (!userRoleData.containsKey(userId)){

            HashMap<String, ArrayList<String>> userPermissionData = new HashMap<>();

            RolePermissionDTO rolePerData = rolePermissionsData.getRolePermissions(userId);

            String roleType = rolePerData.getUserRole();
            ArrayList<String> prmissions = rolePerData.getPermissions();

            userPermissionData.put(roleType, prmissions);

            userRoleData.put(userId, userPermissionData);
        }


    }
















}
