package org.launchcode.git_artsy_backend.Repo;

import org.launchcode.git_artsy_backend.Models.PatronCommissionRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


//Repository interface for CommissionRequest entities.

@Repository
public interface PatronCommissionRequestRepository extends CrudRepository<PatronCommissionRequest, Long> {
}

