package com.school.StudentService.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "class_grade")
public class ClassGrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classGradeId;

    private String className;

    private String franchiseId;

    private String clsRecordId;

    @OneToMany
    private List<ClassSection> Section;


}
