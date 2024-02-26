package com.school.ApiGateway.Utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RolePermissionDTO {

    private String userRole;

    private ArrayList<String> permissions;

}
