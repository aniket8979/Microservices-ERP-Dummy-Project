package com.main.feignService;


import com.main.Transient.StudentEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "student-service")
public interface StudentFeign {

    @GetMapping("/student/getStudentId")
    StudentEntity getStudentId(@RequestParam("studentId") String  studentId);


}
