package com.main.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.main.Transient.ClassSection;
import com.main.feignService.StudentFeign;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Data
@Entity
@Table
public class Fee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int feeId;

    private String studentId;
    private String transectionId;
    @JsonIgnore
    private String franchiseId;
    private String feeType;
    private Double amount;
    private Date dueDate;
    private Double dueAmount;
    private String studentName;
    private String status;
    private String paymentMethod;
    private Double penaltyAmount;
    private String classSection;
    private String classTeacher;

//    @Transient
//   private StudentFeign studentFeign;







}
