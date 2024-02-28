package com.school.LoginService.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;


@Data
@Entity
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   // @Column(unique = true)
    private String schoolName;
    private String schoolEmail;
    private String schoolAddress;
    private String schoolPhone;
    private String description;
    private boolean status;
    private String schoolPhoto;

    @Column(unique = true)
    private String schoolId; // franchise Id

   // private String connectionId; // unique Id
    //private String prefix;
    //private String schoolId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id") 
    private Plans plans;

    @OneToMany(mappedBy = "school")
    @JsonIgnore
    private List<Features> features;



}
