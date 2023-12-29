package com.school.StaffService.Transient;


import com.school.StaffService.Model.TeacherModel;

import java.util.List;

public class ClassGrade {


    private int id;

    private String className;

    private String section;

    private String roomNo;

    private TeacherModel classTeacher;

    private List<StudentModel> classStudents;


    public ClassGrade() {
    }

    public ClassGrade(int id, String className, String section, String roomNo, TeacherModel classTeacher, List<StudentModel> classStudents) {
        this.id = id;
        this.className = className;
        this.section = section;
        this.roomNo = roomNo;
        this.classTeacher = classTeacher;
        this.classStudents = classStudents;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public TeacherModel getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(TeacherModel classTeacher) {
        this.classTeacher = classTeacher;
    }

    public List<StudentModel> getClassStudents() {
        return classStudents;
    }

    public void setClassStudents(List<StudentModel> classStudents) {
        this.classStudents = classStudents;
    }

}
