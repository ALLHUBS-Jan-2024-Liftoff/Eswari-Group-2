package org.launchcode.git_artsy_backend.controllers;

import org.launchcode.git_artsy_backend.models.CommissionRequest;
import org.launchcode.git_artsy_backend.repo.CommissionRequestRepository;

import java.lang.reflect.Constructor;
import java.util.Optional;


//  Controller for managing commission requests.
//  Provides methods to handle HTTP requests for creating, reading, updating, and deleting commission requests.


public class CommissionRequestController {

    // Repository for performing CRUD operations on CommissionRequest entities.
    private CommissionRequestRepository repository;


//      Constructor for CommissionRequestController.
//     Initializes the repository.


    public CommissionRequestController(CommissionRequestRepository repository) {
        this.repository = repository;
    }


//     Retrieves all commission requests.
//     @return a list of all commission requests.

    public Iterable<CommissionRequest> getAllRequests() {
        return repository.findAll();
    }


//     Retrieves a commission request by its ID.

    public CommissionRequest getRequestById(Long id) {
        Optional<CommissionRequest> request = repository.findById(id);
        return request.orElse(null);
    }

    // Creates a new commission request.

    public CommissionRequest createRequest(CommissionRequest request) {
        return repository.save(request);
    }

    //Updates an existing commission request.

    public CommissionRequest updateRequest(Long id, CommissionRequest newRequest) {
        if (repository.existsById(id)) {
            newRequest.setId(id);
            return repository.save(newRequest);
        } else {
            return null;
        }
    }


     //Deletes a commission request by its ID.

    public boolean deleteRequest(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
