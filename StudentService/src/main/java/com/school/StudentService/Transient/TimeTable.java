package com.school.StudentService.Transient;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeTable {

    private String timeTableId;

    private String classId;

    private SubjectCourse subject;

    private TeacherModel teacher;

}