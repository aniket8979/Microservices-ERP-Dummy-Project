package com.school.StaffService.Transient;

import com.school.StaffService.Model.TeacherModel;

import java.util.List;


public class SubjectCourse {

    private int id;

    private String subject;

    private String weight;

    private int hour;

    private List<TeacherModel> subjectTeacher;

    private List<TimeTable> subtimetable;


    public SubjectCourse() {
    }

    public SubjectCourse(int id, String subject, String weight, int hour, List<TeacherModel> subjectTeacher, List<TimeTable> subtimetable) {
        this.id = id;
        this.subject = subject;
        this.weight = weight;
        this.hour = hour;
        this.subjectTeacher = subjectTeacher;
        this.subtimetable = subtimetable;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public List<TeacherModel> getSubjectTeacher() {
        return subjectTeacher;
    }

    public void setSubjectTeacher(List<TeacherModel> subjectTeacher) {
        this.subjectTeacher = subjectTeacher;
    }

    public List<TimeTable> getSubtimetable() {
        return subtimetable;
    }

    public void setSubtimetable(List<TimeTable> subtimetable) {
        this.subtimetable = subtimetable;
    }

}
