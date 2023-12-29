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
@Table(name = "classes")
public class ClassGrade {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String franchiseId;

    @Column(name = "className")
    private String className;

    @Column(name = "section")
    private String section;

    @Column(name = "roomNo")
    private String roomNo;

    @Transient
    private TeacherModel classTeacher;

    @OneToMany(fetch = FetchType.EAGER)
    private List<StudentModel> classStudents;


}
