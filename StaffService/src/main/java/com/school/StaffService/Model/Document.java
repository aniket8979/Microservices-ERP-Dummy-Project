package com.school.StaffService.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "document")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String franchiseId;

    @Column(name = "docUserId")
    private String docUserId;


    @Column(name = "docType")
    private String docType;

    @Column(name = "docNumber")
    private String docNumber;

    @Column(name = "docPath")
    private String docPath;


}
