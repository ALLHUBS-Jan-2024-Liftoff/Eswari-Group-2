package org.launchcode.git_artsy_backend.controllers;

import org.launchcode.git_artsy_backend.models.PatronCommissionRequest;
import org.launchcode.git_artsy_backend.repositories.PatronCommissionRequestRepository;
import org.launchcode.git_artsy_backend.repositories.PatronCommissionRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

// Controller for managing commission requests.
// Provides endpoints for creating, reading, updating, and deleting commission requests.


@RestController
@RequestMapping ("/api/commissions")
public class PatronCommissionRequestController {

    @Autowired
    private PatronCommissionRequestRepository repository;

    // Retrieves all commission requests.
    @GetMapping
    public Iterable<PatronCommissionRequest> getAllRequests() {
        return repository.findAll();
    }

    // Retrieves a commission request by its ID.
    @GetMapping("/{id}")
    public PatronCommissionRequest getRequestById(@PathVariable Long id) {
        Optional<PatronCommissionRequest> request = repository.findById(id);
        return request.orElse(null);
    }

    // Creates a new commission request.
    @PostMapping
    public PatronCommissionRequest createRequest(@RequestBody PatronCommissionRequest request) {
        return repository.save(request);
    }

    // Updates an existing commission request.
    @PutMapping("/{id}")
    public PatronCommissionRequest updateRequest(@PathVariable Long id, @RequestBody PatronCommissionRequest newRequest) {
        if (repository.existsById(id)) {
            newRequest.setId(id);
            return repository.save(newRequest);
        } else {
            return null;
        }
    }

    // Deletes a commission request by its ID.
    @DeleteMapping("/{id}")
    public boolean deleteRequest(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
