package com.main.Transient;


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
public class ClassGrade {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int classGradeId;

    private String className;

    private String clsRecordId;


//    @OneToMany
//    private List<ClassSection> Section;


}
