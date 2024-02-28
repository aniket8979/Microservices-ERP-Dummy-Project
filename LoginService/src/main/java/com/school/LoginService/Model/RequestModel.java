package com.school.LoginService.Model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "requests")
@Data
public class RequestModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String reqId;

    private Date reqDate;

    private String reqDesc;

    private String reqEmail;

    private String reqPhone;

    private String reqMsg;

    private String reqStatus;

}
