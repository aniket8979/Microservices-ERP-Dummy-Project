package com.school.StudentService.DTO;

import com.school.StudentService.Model.StudentModel;
import com.school.StudentService.Model.ClassGrade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private StudentModel studentData;

    private ClassGrade classData;

}
