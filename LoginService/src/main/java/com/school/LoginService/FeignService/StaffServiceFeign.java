package com.school.LoginService.FeignService;


import com.school.LoginService.Transient.TeacherModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "staff-service")
public interface StaffServiceFeign {

    @GetMapping("/teacher/stafffound")
    public TeacherModel isStaffFound(@RequestParam("staffMail") String staffMail);

    @GetMapping("/role/getuserrole")
    public String getUserRoleType(@RequestParam("email") String email);


}
