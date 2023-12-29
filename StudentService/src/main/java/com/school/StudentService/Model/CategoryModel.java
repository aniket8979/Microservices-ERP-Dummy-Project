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
@Table(name = "category")
public class CategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String franchiseId;

    // SRMGZ-Cat-01 : SRMABC01

    // SRM-GZ-H-01 : SRM

    // AKG-GZ : AKG

    // AKGIC-GZ : AKGIC

//    private String recordId;

    private String categoryName;

    private String categoryDescription;

//    private List<StudentModel> categoryStudents;


}
