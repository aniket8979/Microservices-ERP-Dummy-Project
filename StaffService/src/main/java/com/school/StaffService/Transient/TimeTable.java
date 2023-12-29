package com.school.StaffService.Transient;


import com.school.StaffService.Model.TeacherModel;

public class TimeTable {

    private String timeTableId;


    private String classId;


    private SubjectCourse subject;


    private TeacherModel teacher;


    public TimeTable() {
    }

    public TimeTable(String timeTableId, String classId, SubjectCourse subject, TeacherModel teacher) {
        this.timeTableId = timeTableId;
        this.classId = classId;
        this.subject = subject;
        this.teacher = teacher;
    }


    public String getTimeTableId() {
        return timeTableId;
    }

    public void setTimeTableId(String timeTableId) {
        this.timeTableId = timeTableId;
    }


    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public SubjectCourse getSubject() {
        return subject;
    }

    public void setSubject(SubjectCourse subject) {
        this.subject = subject;
    }

    public TeacherModel getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherModel teacher) {
        this.teacher = teacher;
    }
}

