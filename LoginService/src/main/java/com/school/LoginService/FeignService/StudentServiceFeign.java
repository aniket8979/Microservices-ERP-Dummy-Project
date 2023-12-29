package com.school.LoginService.FeignService;

import com.school.LoginService.Transient.StudentModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "student-service")
public interface StudentServiceFeign {

    @PostMapping("/student/studentfound")
    public StudentModel isStudentFound(@RequestParam("email") String email);

    @PostMapping("/student/test")
    public String testStudnet(@RequestParam("test") String test);



}
