package org.launchcode.git_artsy_backend.Repo;

import org.launchcode.git_artsy_backend.models.CommissionRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


//Repository interface for CommissionRequest entities.

@Repository
public interface CommissionRequestRepository extends CrudRepository<CommissionRequest, Long> {
}

