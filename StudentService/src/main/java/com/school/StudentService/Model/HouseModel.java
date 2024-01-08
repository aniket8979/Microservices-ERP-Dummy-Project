package com.school.StudentService.Model;


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
@Table(name = "house")
public class HouseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String franchiseId;

    private String houseId;

    private String houseName;

    private String houseColour;

    private String houseDescription;

//    private List<StudentModel> houseStudent;
}