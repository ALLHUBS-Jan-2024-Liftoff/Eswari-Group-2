package org.launchcode.git_artsy_backend.controllers;

import org.launchcode.git_artsy_backend.models.PatronCommissionRequest;
import org.launchcode.git_artsy_backend.models.User;
import org.launchcode.git_artsy_backend.repositories.PatronCommissionRequestRepository;
import org.launchcode.git_artsy_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/commissions")
public class PatronCommissionRequestController {

    @Autowired
    private PatronCommissionRequestRepository patronCommissionRequestRepository;

    @Autowired
    private UserRepository userRepository;

    // Create a new commission request
    @PostMapping("/submit")
    public ResponseEntity<PatronCommissionRequest> submitRequest(
            @RequestParam("userId") Long userId,
            @RequestBody PatronCommissionRequest request) {

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            request.setArtist(user);  // Set the user's object as the artist in the request

            PatronCommissionRequest savedRequest = patronCommissionRequestRepository.save(request);
            return ResponseEntity.ok(savedRequest);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Fetch all commission requests
    @GetMapping
    public ResponseEntity<List<PatronCommissionRequest>> getAllRequests() {
        List<PatronCommissionRequest> requests = patronCommissionRequestRepository.findAll();
        return ResponseEntity.ok(requests);
    }

    // Fetch a single commission request by ID
    @GetMapping("/{id}")
    public ResponseEntity<PatronCommissionRequest> getRequestById(@PathVariable Long id) {
        Optional<PatronCommissionRequest> requestOptional = patronCommissionRequestRepository.findById(id);
        if (requestOptional.isPresent()) {
            return ResponseEntity.ok(requestOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Update a commission request by ID
    @PutMapping("/{id}")
    public ResponseEntity<PatronCommissionRequest> updateRequest(
            @PathVariable Long id,
            @RequestBody PatronCommissionRequest updatedRequest) {

        Optional<PatronCommissionRequest> requestOptional = patronCommissionRequestRepository.findById(id);
        if (requestOptional.isPresent()) {
            PatronCommissionRequest existingRequest = requestOptional.get();

            // Update the necessary fields
            existingRequest.setDetails(updatedRequest.getDetails());
            existingRequest.setDescription(updatedRequest.getDescription());
            existingRequest.setSubject(updatedRequest.getSubject());

            PatronCommissionRequest savedRequest = patronCommissionRequestRepository.save(existingRequest);
            return ResponseEntity.ok(savedRequest);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Delete a commission request by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        if (patronCommissionRequestRepository.existsById(id)) {
            patronCommissionRequestRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
