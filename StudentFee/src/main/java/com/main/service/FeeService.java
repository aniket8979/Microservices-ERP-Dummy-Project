package com.main.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.Transient.StudentEntity;
import com.main.config.UtilitiesServices;
import com.main.entity.Fee;
import com.main.exception.GlobalExceptionHandler;
import com.main.exception.ResponseClass;
import com.main.feignService.StudentFeign;
import com.main.repo.FeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class FeeService {

    @Autowired
    private FeeRepo feeRepo;




    @Autowired
    private StudentFeign studentFeign;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ObjectMapper objectMapper;

//   String uniqueId = "gir01";
    public ResponseEntity<?> feePay(String franchiseId,String uniqueId,String studentId, String feeType, String data) {
        try {
            Map<String, Object> jsonData = objectMapper.readValue(data, new TypeReference<>() {});
            Fee fee = new Fee();
            fee.setFeeType(feeType);
            StudentEntity student = studentFeign.getStudentId(studentId);
            System.out.println("Student info : "+student.getClassSection().getSectionId());
            if (student == null) {
                return GlobalExceptionHandler.notFoundException("student id not found");
            }

            String genTransectioId;

            // code to generate Transection Id
            while(true){
                genTransectioId = UtilitiesServices.generateRecordId(uniqueId, "fee",10);
                boolean found = feeRepo.existsBytransectionId(genTransectioId);
                if(!found){
                    fee.setTransectionId(genTransectioId);
                    break;
                }
            }

            System.out.println("this is the newly generated transection Id"+genTransectioId);
            fee.setStudentId(studentId);

            DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
            LocalDateTime dueDate = LocalDateTime.parse((String) jsonData.get("dueDate"), formatter);
            fee.setDueDate(java.sql.Timestamp.valueOf(dueDate));
            fee.setStudentName(student.getName());
            fee.setAmount((Double) jsonData.get("amount"));
            fee.setFranchiseId(franchiseId);
            fee.setDueAmount((Double) jsonData.get("dueAmount"));
            fee.setStatus((String) jsonData.get("status"));
            fee.setPaymentMethod((String) jsonData.get("paymentMethod"));
            fee.setPenaltyAmount((Double) jsonData.get("penaltyAmount"));

            try {

                String teacherName = student.getClassSection().getClassTeacher().getName();
                fee.setClassTeacher(teacherName);
            } catch (Exception e){
                System.out.println("null pointer exception");

            }

            fee.setClassSection(student.getClassSection().getSectionName());

            //  Newly added information


           boolean isEmailSent = emailService.sendSuccessFeePay(student.getEmail(), fee.getFeeId());
           if(!isEmailSent)
           {
               System.out.println("Notification not send");
           }else
           {
               System.out.println("Notification not send");
           }
            System.out.println("2");
            feeRepo.save(fee);
            System.out.println("3");

            return ResponseClass.responseSussess("Fee Payment Successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return GlobalExceptionHandler.badRequest("wrong json data ");
        }
    }
}
