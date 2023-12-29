package com.school.LoginService.Transient;

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

}