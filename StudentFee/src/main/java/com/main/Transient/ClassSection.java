package com.main.Transient;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassSection {


//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;

    private String franchiseId;

    private String sectionId;

    private String sectionName;

    @ManyToOne
    private ClassGrade classGrade;

    @Transient
    @OneToOne
    private TeacherModel classTeacher;

//    @OneToMany
//    private List<StudentModel> classStudents;


}