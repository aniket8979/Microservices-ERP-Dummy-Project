package com.school.StudentService.DTO;

import com.school.StudentService.Model.StudentModel;
import com.school.StudentService.Model.ClassSection;
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

    private ClassSection classData;

}
