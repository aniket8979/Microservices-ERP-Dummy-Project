package com.school.LoginService.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long adminId;

  //  @Column(unique = true)
    private String adminEmail;

    private String adminName;

    private String adminPhone;
    private String adminAddress;

    private String adminGender;

    private String adminBlood;
    private String adminPassword;

    private String adminPhoto;

    private String adminRole;



}