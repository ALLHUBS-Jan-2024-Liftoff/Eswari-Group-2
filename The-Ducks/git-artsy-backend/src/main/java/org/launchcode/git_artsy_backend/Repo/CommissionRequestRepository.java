package org.launchcode.git_artsy_backend.repo;

import org.launchcode.git_artsy_backend.models.CommissionRequest;



 //Repository interface for CommissionRequest entities.

@Repository
public interface CommissionRequestRepository extends CrudRepository<CommissionRequest, Long> {
}

