package com.school.LoginService.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "super_admin")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SuperAdminModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String email;

    private String adminName;

    private String adminRole;

    private String uniqueId;

    private String franchiseId;

    private String schoolName;

    private String schoolAddress;

    private String logoImage;

    private String adminFeild;

}