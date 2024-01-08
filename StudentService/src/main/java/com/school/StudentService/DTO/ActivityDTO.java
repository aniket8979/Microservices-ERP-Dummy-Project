package com.school.StudentService.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ActivityDTO {

    private String activityName;

    private String activityId;

    private String activityColour;

    private String activityIcon;

    private String description;

    private Date activityDate;
}
