package com.school.StaffService.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "allrole")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AllRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String franchiseId;

    private String roleId;

    private String roleType;

    private String roleName;

}