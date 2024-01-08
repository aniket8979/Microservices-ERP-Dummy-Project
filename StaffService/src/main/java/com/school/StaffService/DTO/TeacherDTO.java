package com.school.StaffService.DTO;


import com.school.StaffService.Model.Document;
import com.school.StaffService.Model.TeacherModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeacherDTO {
    private TeacherModel userReq;
    private String roleReq;
    private List<Document> docReq;


}

