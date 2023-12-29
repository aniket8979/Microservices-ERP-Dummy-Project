package com.school.StaffService.Model;

import com.school.StaffService.Transient.ClassGrade;
import com.school.StaffService.Transient.SubjectCourse;
import com.school.StaffService.Transient.TimeTable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;



@Entity
@Table(name = "teacher")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TeacherModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    ////////////////////////////////////////////////////////////////

    private String franchiseId;

    private String userId;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "dateOfBirth")
    private Date dateOfBirth;

    @Column(name = "religion")
    private String religion;

    @Column(name = "bloodGroup")
    private String bloodGroup;

    @Column(name = "address")
    private String address;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone")
    private String phone;

    //////////////////////////////////////////////////////////////////

    @Column(name = "username")
    private String username;

    // Teacher's Personal and Other Important Information

    @Column(name = "maritalStatus")
    private String maritalStatus;

    @Column(name = "qualification")
    private String qualification;

    // Teachers HumanResource related Information

    @Column(name = "department")
    private String department;

    @Column(name = "designation")
    private String designation;

    @Column(name = "joinSalary")
    private int joinSalary;

    @Column(name = "joiningDate")
    private Date joiningDate;

    @Column(name = "status")
    private String status;

    // Teacher's Bank Details

    @Column(name = "bankName")
    private String bankName;

    @Column(name = "accountNo")
    private String accountNo;

    @Column(name = "accHolder")
    private String accHolder;

    @Column(name = "ifscCode")
    private String ifscCode;

    @Column(name = "branch")
    private String branch;

    @Column(name = "branchIdCode")
    private String branchIdCode;

    @Column(name = "dpPath")
    private String dpPath;

    @Transient
    private ClassGrade classid;

    @Transient
    private List<TimeTable> teaTimeTable;

    @Transient
    private List<SubjectCourse> subjects;


}