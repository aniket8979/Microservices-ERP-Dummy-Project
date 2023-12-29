package com.school.StudentService.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class StudentModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Fields from User Super Class

    private String userId;

    private String franchiseId;

    @Column(name = "name")
    private String name;

    private String motherName;

    private String fatherName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "dateOfBirth")
    private java.util.Date dateOfBirth;

    @Column(name = "religion")
    private String religion;

    @Column(name = "bloodGroup")
    private String bloodGroup;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    //

    @Column(name = "status")
    private String status;

    @Column(name = "section")
    private String section;

    @Column(name = "studentHouse")
    private String studentHouse;

    @Column(name = "studentClub")
    private String studentClub;

    @Column(name = "birthPlace")
    private String birthPlace;

    @Column(name = "motherTongue")
    private String motherTongue;

    @Column(name = "postalCode")
    private int postalCode;

    @Column(name = "state")
    private String state;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "password")
    private String password;

    @Column(name = "previousSchool")
    private String previousSchool;

    @Column(name = "preSchoolAdd")
    private String preSchoolAdd;

    @Column(name = "leavingPurpose")
    private String leavingPurpose;

    @Column(name = "addmissionDate")
    private Date addmissionDate;

    @Column(name = "physicalHandicap")
    private String phyHandicap;

    @Column(name = "dpPath")
    private String dpPath;

    @Transient
    private ClassGrade classGrade;

}