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
    private Long id;

    // Create a new commission request
    @PostMapping("api/commissions/submit")
    public ResponseEntity<PatronCommissionRequest> submitRequest(
            @RequestParam("artistId") Long artistId,
            @RequestBody PatronCommissionRequest request) {

        Optional<User> artistOptional = userRepository.findById(artistId);
        if (artistOptional.isPresent()) {
            User artist = artistOptional.get();
            request.setArtist(artist);  // Set the artist's user object

            PatronCommissionRequest savedRequest = patronCommissionRequestRepository.save(request);
            return ResponseEntity.ok(savedRequest);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Fetch all commission requests
    @GetMapping("api/commissions")
    public ResponseEntity<List<PatronCommissionRequest>> getAllRequests() {
        List<PatronCommissionRequest> requests = patronCommissionRequestRepository.findAll();
        return ResponseEntity.ok(requests);
    }

    // Fetch a single commission request by ID
    @GetMapping("/api/commissions/{id}")
    public ResponseEntity<PatronCommissionRequest> getRequestById(@PathVariable Long id) {
        Optional<PatronCommissionRequest> requestOptional = patronCommissionRequestRepository.findById(id);
        if (requestOptional.isPresent()) {
            return ResponseEntity.ok(requestOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Update a commission request by ID
    @PutMapping("/api/commissions/{id}")
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
    @DeleteMapping("/api/commissions/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        this.id = id;
        if (patronCommissionRequestRepository.existsById(id)) {
            patronCommissionRequestRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
