package com.main.repo;

import com.main.entity.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeeRepo extends JpaRepository<Fee,Integer> {
        Fee findByFeeId(int feeId);
        List<Fee> findByFranchiseId(String id);

        List<Fee> findByFranchiseIdAndStudentId(String franchiseId, String studentId);

       Fee findByFranchiseIdAndTransectionId(String franchiseId, String transectionId);

        boolean existsBytransectionId(String userId);
}
