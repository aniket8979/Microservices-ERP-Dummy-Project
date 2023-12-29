package com.school.StaffService.Repo;

import com.school.StaffService.Model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocRepo extends JpaRepository<Document, Integer> {


}