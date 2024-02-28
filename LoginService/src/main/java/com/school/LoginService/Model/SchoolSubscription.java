package com.school.LoginService.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Data
@Table
public class SchoolSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int subsId;
    private String schoolId;
    private Double price;
    private int plan;
    private LocalDateTime purchaseDate;
    private String email;
    private String phoneNo;
    private boolean status;




}
