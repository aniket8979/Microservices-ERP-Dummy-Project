package com.school.LoginService.Model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String schoolName;

    private String schoolEmail;

    private String schoolAddress;

    private String schoolPhone;

    private String description;

    private boolean status;

    private String schoolPhoto;

    private String schoolId;

    @ManyToOne
    @JoinColumn(name = "planId")
    private Plans plans;

    @OneToMany
    private List<Features> features;


}