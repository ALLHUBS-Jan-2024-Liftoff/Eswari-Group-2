package org.launchcode.git_artsy_backend.controllers;

import org.launchcode.git_artsy_backend.models.PatronCommissionRequest;
import org.launchcode.git_artsy_backend.models.User;
import org.launchcode.git_artsy_backend.models.dto.PatronDTO;
import org.launchcode.git_artsy_backend.repositories.PatronCommissionRequestRepository;
import org.launchcode.git_artsy_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("api/commissions")
public class PatronCommissionRequestController {

    @Autowired
    private PatronCommissionRequestRepository patronCommissionRequestRepository;

    @Autowired
    private UserRepository userRepository;

    // Create a new commission request
    @PostMapping("/submit")
    public ResponseEntity<PatronCommissionRequest> submitRequest(
            @RequestBody PatronDTO outgoingCommission
            )
            {

        Optional<User> userOptional = userRepository.findById(outgoingCommission.getArtist_id());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            PatronCommissionRequest patronCommissionRequest = new PatronCommissionRequest(
                    outgoingCommission.getSubject(), outgoingCommission.getDetails(), outgoingCommission.getDescription());
            patronCommissionRequest.setArtist(user);

            PatronCommissionRequest savedRequest = patronCommissionRequestRepository.save(patronCommissionRequest);
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

//    // Fetch all commission requests for a particular artist by user ID
//    @GetMapping("/artist/{artistId}")
//    public ResponseEntity<List<PatronCommissionRequest>> getRequestsByArtistId(@PathVariable Long artistId) {
//        Optional<User> artistOptional = userRepository.findById(artistId);
//
//        if (artistOptional.isPresent()) {
//            List<PatronCommissionRequest> requests = patronCommissionRequestRepository.findByArtist(artistOptional.get());
//            return ResponseEntity.ok(requests);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//    }

    // Fetch all commission requests for a particular artist by user ID
    @GetMapping("/artist/{artistId}")
    public ResponseEntity<List<PatronDTO>> getRequestsByArtistId(@PathVariable Long artistId) {
        // Find the artist by ID
        Optional<User> artistOptional = userRepository.findById(artistId);

        if (artistOptional.isPresent()) {
            // Get commission requests for the artist
            List<PatronCommissionRequest> requests = patronCommissionRequestRepository.findByArtist(artistOptional.get());

            // Convert to PatronDTO list without using map
            List<PatronDTO> dtoList = new ArrayList<>();
            for (PatronCommissionRequest request : requests) {
                PatronDTO dto = new PatronDTO(
                        request.getId(),  // ID of the request
                        //request.getArtist().getUser_id(),  // ID of the artist
                        request.getSubject(),  // Subject of the request
                        request.getDetails(),  // Details of the request
                        request.getDescription()  // Description of the request
                );
                dtoList.add(dto);
            }

            return ResponseEntity.ok(dtoList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
