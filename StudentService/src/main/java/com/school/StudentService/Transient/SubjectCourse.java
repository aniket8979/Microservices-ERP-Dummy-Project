package com.school.StudentService.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectCourse {

    private int id;


    private String subject;


    private String weight;


    private int hour;

    private List<TeacherModel> subjectTeacher;


    private List<TimeTable> subtimetable;

}