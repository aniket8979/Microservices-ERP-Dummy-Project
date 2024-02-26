package com.school.LoginService.Controller;

import com.netflix.discovery.converters.Auto;
import com.school.LoginService.Model.FeaPermission;
import com.school.LoginService.Model.Features;
import com.school.LoginService.Service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Permission;

@RequestMapping("/per")
@RestController
public class PermissionController {

    @Autowired
    private PermissionService permissionService;


    @PostMapping("/addPermission/{feaId}")
    public ResponseEntity<?> addPermission(@PathVariable int feaId,@RequestBody FeaPermission permission)
    {
        return permissionService.savePermission(feaId,permission);

    }



    @GetMapping("/getAllPermission")
    public ResponseEntity<?> getAllPermission()
    {
        return permissionService.getAllPermission();

    }

    @GetMapping("/getAllPerByFeaId")
    public ResponseEntity<?> getAllPermissionByFeaId(@RequestParam  int featureId)
    {
        return permissionService.getAllPerByFeaId(featureId);

    }


    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam int permissionId)
    {
        return permissionService.getById(permissionId);

    }

    @PutMapping("/editById/{id}")
    public ResponseEntity<?> editById(@PathVariable int id,@RequestBody FeaPermission permission)
    {
        return permissionService.editById(id,permission);

    }


    @DeleteMapping("/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam int permissionId )
    {
        return permissionService.deleteById(permissionId);

    }


}
