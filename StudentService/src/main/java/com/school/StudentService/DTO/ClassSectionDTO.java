package com.school.StudentService.DTO;

import com.school.StudentService.Model.ClassGrade;
import com.school.StudentService.Model.StudentModel;
import com.school.StudentService.Transient.TeacherModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClassSectionDTO {

    private String sectionId;

    private String sectionName;

    private String teacherName;

    private int totalStudent;


}
