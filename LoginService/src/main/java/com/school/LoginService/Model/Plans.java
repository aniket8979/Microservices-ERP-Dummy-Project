package com.school.LoginService.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Data
@Entity
public class Plans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int planId;
    private String planName;
    private Double price;
    public enum DurationType {
        DAYS,
        WEEKS,
        MONTHS,
        YEARS
    }
    private DurationType type;
    private int value;
    private int studentLimit;
    private boolean status;
    private LocalDateTime purchaseDate;

    @OneToMany(mappedBy = "plans")
    private List<School> school;

    @OneToMany
    private List<Features> features;
}
