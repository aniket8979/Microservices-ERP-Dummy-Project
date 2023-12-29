package com.school.StudentService.FeignService;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "staff-service")
public interface FeignServices {



}
