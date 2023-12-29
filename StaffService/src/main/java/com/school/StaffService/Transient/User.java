package com.school.StaffService.Transient;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.util.Date;


@MappedSuperclass
public class User {


    public String userId;

    @Column(name = "name")
    public String name;

    @Column(name = "gender")
    public String gender;

    @Column(name = "dateOfBirth")
    public Date dateOfBirth;

    @Column(name = "religion")
    public String religion;

    @Column(name = "bloodGroup")
    public String bloodGroup;

    @Column(name = "address")
    public String address;

    @Column(name = "email")
    public String email;

    @Column(name = "phone")
    public String phone;




    public User() {
    }

    public User(String userId, String name, Date dateOfBirth, String gender, String religion, String bloodGroup, String address, String email, String phone) {
        this.userId = userId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.religion = religion;
        this.bloodGroup = bloodGroup;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
