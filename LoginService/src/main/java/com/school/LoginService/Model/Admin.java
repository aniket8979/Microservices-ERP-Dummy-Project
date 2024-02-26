package com.school.LoginService.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "super_admin")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;

    private String serviceId;

    @Column(unique = true)
    private String adminEmail;

    private String adminName;

    private String adminPhone;

    private String adminAddress;

    private String adminGender;

    private String adminBlood;

    private String adminPassword;

    private String adminPhoto;

    private String adminRole = "ADMIN";


}