package com.school.StudentService.FeignService;


import com.school.StudentService.Transient.TeacherModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "staff-service")
public interface StaffServiceFeign {

    @GetMapping("/teacher/stafffound")
    public TeacherModel isStaffFound(@RequestParam("staffMail") String staffMail);

    @GetMapping("/teacher/staff-by-userid")
    public TeacherModel staffByUserId(@RequestParam("userId") String userId);



}
