package com.school.StaffService.Repo;

import com.school.StaffService.Model.AllRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AllRoleRepo extends JpaRepository<AllRole, Integer> {


    List<AllRole> findAllByfranchiseId(String franchiseId);
    AllRole getReferenceByroleId(String roleId);

    boolean existsByroleId(String roleId);

    AllRole findByroleId(String roleId);
}