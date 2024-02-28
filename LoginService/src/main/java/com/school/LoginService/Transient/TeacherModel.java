package com.school.LoginService.Transient;


import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TeacherModel {


    private int id;

    private String franchiseId;

    ////////////////////////////////////////////////////////////////
    public String userId;

    public String name;

    public String gender;

    public Date dateOfBirth;

    public String religion;

    public String bloodGroup;

    public String address;

    public String email;

    public String phone;

    //////////////////////////////////////////////////////////////////


    private String username;

    // Teacher's Personal and Other Important Information

    private String maritalStatus;

    private String qualification;

    // Teachers HumanResource related Information

    private String department;

    private String designation;

    private int joinSalary;

    private Date joiningDate;

    private String status;

    // Teacher's Bank Details

    private String bankName;

    private String accountNo;

    private String accHolder;

    private String ifscCode;

    private String branch;

    private String branchIdCode;

    private String dpPath;

//    @Transient
//    private ClassGrade classid;
//
//    @Transient
//    private List<TimeTable> teaTimeTable;
//
//    @Transient
//    private List<SubjectCourse> subjects;

}