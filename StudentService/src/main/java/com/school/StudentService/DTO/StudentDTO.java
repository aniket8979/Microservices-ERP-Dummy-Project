package com.school.StudentService.DTO;

import com.school.StudentService.Model.StudentModel;
import com.school.StudentService.Model.ClassSection;
import com.school.StudentService.Transient.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private StudentModel studentInfo;

    private String roleInfo;

    private ClassSection classData;
    
}
