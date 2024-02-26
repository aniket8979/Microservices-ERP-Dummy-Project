package com.school.ApiGateway.Feign;


import com.school.ApiGateway.Utils.RolePermissionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "staff-service")
public interface RolePermissionsData {

    @GetMapping("/role_permissions")
    public RolePermissionDTO  getRolePermissions(@RequestParam("userId") String userId);



}
