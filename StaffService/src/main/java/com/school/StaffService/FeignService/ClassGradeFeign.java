package com.school.StaffService.FeignService;

import com.school.StaffService.Transient.ClassGrade;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "student-service")
public interface ClassGradeFeign {

    @GetMapping("/classgrade")
    ClassGrade getclassgrade(int id);


}
