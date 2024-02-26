//package com.school.LoginService.Controller;
//
//
//import com.school.LoginService.Model.Admin;
//import com.school.LoginService.Service.SuperAdminService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/admin")
//public class SuperAdminController {
//
//
//    @Autowired
//    private SuperAdminService superAdminService;
//
//    @GetMapping("/{thename}")
//    public ResponseEntity<String> micCheck(@PathVariable("thename")String thatName){
//        return ResponseEntity.ok(thatName);
//    }
//
//
//    @PostMapping("/superadmin/register")
//    public ResponseEntity<String> registerSuperAdmin(@RequestHeader("SECRET")String secret, @RequestBody Admin superAdminInfo){
//        Object admin = superAdminService.createAdmin(secret, superAdminInfo);
//        if(admin != null){
//            if(admin.equals(false)){
//                return ResponseEntity.ok("UniqueId Already Exists");
//            }
//            return ResponseEntity.ok(admin.toString());
//        }
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid SecretKey");
//    }
//
////    @PostMapping(value = "/record-status/{recordId}")
////    public ResponseEntity<Object> recordIdStatus(@PathVariable("recordId") String recordId){
////        boolean isExist =
////    }
//
//}