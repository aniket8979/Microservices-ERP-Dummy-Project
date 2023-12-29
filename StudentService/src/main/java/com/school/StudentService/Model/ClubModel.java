package com.school.StudentService.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "club")
public class ClubModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String franchiseId;

    private String clubName;

    private String clubColour;

    private String clubIcon;

    private String clubDescription;

//    @ManyToMany
//    private ActivityModel activity;

//    private List<StudentModel> clubStudents;


}
