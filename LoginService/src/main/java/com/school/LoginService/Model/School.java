package com.school.LoginService.Model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @Column(unique = true)
    private String schoolId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private Plans plans;

    @OneToMany(mappedBy = "school")
    @JsonIgnore
    private List<Features> features;


}