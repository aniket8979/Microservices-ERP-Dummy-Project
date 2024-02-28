package com.school.LoginService.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private boolean status;


    @OneToMany(mappedBy = "features", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FeaPermission> feaPermission;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "schoolId", referencedColumnName = "schoolId")
    private School school;

    @ManyToOne
    @JsonIgnore
    private Plans plans;

}
