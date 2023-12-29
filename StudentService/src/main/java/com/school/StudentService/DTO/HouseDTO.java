package com.school.StudentService.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HouseDTO {

    private int id;

    private String houseName;

    private String houseColour;

    private String houseDescription;

}
