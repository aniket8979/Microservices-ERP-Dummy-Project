package com.school.StaffService.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentModel extends User {


    private int id;

    private String status;

    private String section;

    private String studentHouse;

    private String studentClub;

    private String birthPlace;

    private String motherTongue;

    private int postalCode;

    private String state;

    private String nationality;

    private String previousSchool;

    private String preSchoolAdd;

    private String leavingPurpose;

    private Date addmissionDate;

    private String phyHandicap;

    private String dpPath;
    
    private ClassGrade classGrade;

}