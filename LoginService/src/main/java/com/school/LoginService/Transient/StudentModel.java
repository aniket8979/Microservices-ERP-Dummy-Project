package com.school.LoginService.Transient;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentModel extends User {


        private int id;

        // Fields from User Super Class

        private String userId;

        private String schoolId;

        private String name;

        private String gender;

        private java.util.Date dateOfBirth;

        private String religion;

        private String bloodGroup;

        private String address;

        private String email;

        private String phone;

        //

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

        private java.sql.Date addmissionDate;

        private String phyHandicap;

        private String dpPath;

}
