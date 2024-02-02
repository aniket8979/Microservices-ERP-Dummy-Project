package com.main.controller;

import com.main.Transient.StudentEntity;
import com.main.entity.Fee;
import com.main.exception.ResponseClass;
import com.main.feignService.StudentFeign;
import com.main.repo.FeeRepo;
import com.main.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/fee")
public class FeeController {

    @Autowired
    private FeeService feeService;

    @Autowired
    private StudentFeign studentFeign;

    @Autowired
    private FeeRepo feeRepo;

    @PostMapping("/feePay")
    public ResponseEntity<?> feePay(
            @RequestHeader("franchiseId") String franchiseId,
            @RequestHeader("uniqueId") String uniqueId,
            @RequestHeader("roleType") String roleType,
            @RequestParam("studentId") String studentId ,
            @RequestParam("feeType") String feeType,
            @RequestParam("data") String data)
    {
        System.out.println("fee pay");
        if(roleType.equals("ADMIN"))
        {
            System.out.println("1");
            ResponseEntity<?> response = feeService.feePay(franchiseId,uniqueId,studentId,feeType,data);
            return response;
        }
        return ResponseClass.responseFailure("access denied");

    }

    @GetMapping("/getAllFee")
    public ResponseEntity<?> getFee( @RequestHeader("franchiseId") String franchiseId,
                                     @RequestHeader("roleType") String roleType)
    {
        if(roleType.equals("ADMIN"))
        {
            List<Fee> fees = feeRepo.findByFranchiseId(franchiseId);
            return ResponseEntity.status(HttpStatus.OK).body(fees);
        }

        return ResponseClass.responseFailure("access denied");
    }

    @GetMapping("/getFeeStudent")
    public ResponseEntity<?> getStudentFee( @RequestHeader("franchiseId") String franchiseId, @RequestHeader("roleType") String roleType, @RequestParam("studentId") String studentId)
    {
        if(roleType.equals("ADMIN")){
            List<Fee> fees = feeRepo.findByFranchiseIdAndStudentId(franchiseId,studentId);
            System.out.println(studentFeign.getStudentId(studentId));


            return ResponseEntity.status(HttpStatus.OK).body(fees);
        }

        return ResponseClass.responseFailure("access denied");
    }

    @DeleteMapping("/deleteByStudentId")
    public ResponseEntity<?> deleteStudentFee( @RequestHeader("franchiseId") String franchiseId, @RequestHeader("roleType") String roleType, @RequestParam("studentId") String studentId)
    {
        if(roleType.equals("ADMIN")){
            List<Fee> fees = feeRepo.findByFranchiseIdAndStudentId(franchiseId,studentId);
            feeRepo.deleteAll(fees);
            return ResponseClass.responseSussess("student data deleted");
        }

        return ResponseClass.responseFailure("access denied");
    }


    @DeleteMapping("/deleteByTransectionId")
    public ResponseEntity<?> deleteStudentFeeByTransId( @RequestHeader("franchiseId") String franchiseId, @RequestHeader("roleType") String roleType, @RequestParam("transId") String transId)
    {
        if(roleType.equals("ADMIN")){
            Fee fees = feeRepo.findByFranchiseIdAndTransectionId(franchiseId,transId);
            feeRepo.delete(fees);
            return ResponseEntity.status(HttpStatus.OK).body(fees);
        }

        return ResponseClass.responseFailure("access denied");
    }



}
