package com.school.LoginService.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
public class Features {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int featureId;
    private String featureName;
    private String featureUrl;


    @OneToMany(mappedBy = "features")
    private List<FeaPermission> feaPermission;

    @ManyToOne
    private School school;

    @ManyToOne
    private Plans plans;

}
