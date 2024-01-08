package com.school.StudentService.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "activity")
public class

ActivityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String activityId;

    private String franchiseId;

    private String activityName;

    private String activityColour;

    private String activityIcon;

    private String description;

    private Date activityDate;

//    @ManyToMany
//    private ClubModel club;

}
