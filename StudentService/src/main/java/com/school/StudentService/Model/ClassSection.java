package com.school.StudentService.Model;


import com.school.StudentService.Transient.TeacherModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "class_section")
public class ClassSection {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String franchiseId;

    private String sectionId;

    private String sectionName;

    @ManyToOne
    private ClassGrade classGrade;

    @Transient
    @OneToOne
    private TeacherModel classTeacher;

    @OneToMany
    private List<StudentModel> classStudents;


}