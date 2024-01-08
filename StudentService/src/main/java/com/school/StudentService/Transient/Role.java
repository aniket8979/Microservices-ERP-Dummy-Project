package com.school.StudentService.Transient;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    private int id;

    private String roleUserId;

    private String franchiseId;

    private String email;

    private String roleId;

    private String role;

    private String roleType;

}
