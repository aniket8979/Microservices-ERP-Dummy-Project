package com.school.LoginService.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class FeaPermission {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int  feaPerId;
    private String perName;
    private String perUrl;


    @ManyToOne
    @JsonIgnore
    private Features features;


}

