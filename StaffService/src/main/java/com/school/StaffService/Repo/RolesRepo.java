package com.school.StaffService.Repo;

import com.school.StaffService.Model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepo extends JpaRepository<Roles, Integer> {


    Roles getReferenceByemail(String email);

    Roles getReferenceByroleUserId(String userId);

}
