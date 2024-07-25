package org.launchcode.git_artsy_backend.Controllers;

import org.launchcode.git_artsy_backend.Models.CommissionRequest;
import org.launchcode.git_artsy_backend.Repo.CommissionRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

// Controller for managing commission requests.
// Provides endpoints for creating, reading, updating, and deleting commission requests.


//@RestController
//@RequestMapping("/api/commissions")
public class CommissionRequestController {

    @Autowired
    private CommissionRequestRepository repository;

    // Retrieves all commission requests.
    @GetMapping
    public Iterable<CommissionRequest> getAllRequests() {
        return repository.findAll();
    }

    // Retrieves a commission request by its ID.
    @GetMapping("/{id}")
    public CommissionRequest getRequestById(@PathVariable Long id) {
        Optional<CommissionRequest> request = repository.findById(id);
        return request.orElse(null);
    }

    // Creates a new commission request.
    @PostMapping
    public CommissionRequest createRequest(@RequestBody CommissionRequest request) {
        return repository.save(request);
    }

    // Updates an existing commission request.
    @PutMapping("/{id}")
    public CommissionRequest updateRequest(@PathVariable Long id, @RequestBody CommissionRequest newRequest) {
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
