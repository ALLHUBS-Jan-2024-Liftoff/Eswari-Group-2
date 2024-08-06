package org.launchcode.git_artsy_backend.repositories;

import org.launchcode.git_artsy_backend.models.PatronCommissionRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


//Repository interface for CommissionRequest entities.

@Repository
public interface PatronCommissionRequestRepository extends CrudRepository<PatronCommissionRequest, Long> {
}
