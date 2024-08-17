package org.launchcode.git_artsy_backend.repositories;

import org.launchcode.git_artsy_backend.models.PatronCommissionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//Repository interface for CommissionRequest entities.

@Repository
public interface PatronCommissionRequestRepository extends JpaRepository<PatronCommissionRequest, Long> {
}
