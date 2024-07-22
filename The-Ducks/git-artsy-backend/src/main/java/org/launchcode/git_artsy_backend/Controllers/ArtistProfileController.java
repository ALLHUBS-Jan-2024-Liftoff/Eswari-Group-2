package org.launchcode.git_artsy_backend.Controllers;

import org.launchcode.git_artsy_backend.Models.ArtistProfile;
import org.launchcode.git_artsy_backend.Repo.ArtistProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/artist-profiles")
@CrossOrigin(origins = "http://localhost:8082")
public class ArtistProfileController {
    @Autowired
    private ArtistProfileRepo artistProfileRepo;

    @PostMapping("/new")
    public ResponseEntity<ArtistProfile> createArtistProfile(@RequestBody ArtistProfile artistProfile) {
        artistProfile.setCreatedAt(LocalDateTime.now());
        artistProfile.setUpdatedAt(LocalDateTime.now());
        try {
            ArtistProfile savedArtistProfile = artistProfileRepo.save(artistProfile);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedArtistProfile);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/all")
    public ResponseEntity<List<ArtistProfile>> getAllArtistProfiles() {
        List<ArtistProfile> artistProfiles = artistProfileRepo.findAll();
        return ResponseEntity.ok(artistProfiles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistProfile> getArtistProfileById(@PathVariable Long id) {
        Optional<ArtistProfile> optionalArtistProfile = artistProfileRepo.findById(id);
        if (optionalArtistProfile.isPresent()) {
            return ResponseEntity.ok(optionalArtistProfile.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistProfile> updateArtistProfile(@PathVariable Long id, @RequestBody ArtistProfile updatedArtistProfile) {
        Optional<ArtistProfile> existingArtistProfile = artistProfileRepo.findById(id);
        if (existingArtistProfile.isPresent()) {
            ArtistProfile artistProfileToUpdate = existingArtistProfile.get();
            artistProfileToUpdate.setName(updatedArtistProfile.getName());
            artistProfileToUpdate.setLocation(updatedArtistProfile.getLocation());
            artistProfileToUpdate.setEmail(updatedArtistProfile.getEmail());
            artistProfileToUpdate.setPhone(updatedArtistProfile.getPhone());
            artistProfileToUpdate.setProfilePic(updatedArtistProfile.getProfilePic());
            artistProfileToUpdate.setBioDescription(updatedArtistProfile.getBioDescription());
            artistProfileToUpdate.setUpdatedAt(LocalDateTime.now());
            try {
                ArtistProfile savedArtistProfile = artistProfileRepo.save(artistProfileToUpdate);
                return ResponseEntity.ok(savedArtistProfile);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtistProfile(@PathVariable Long id) {
        Optional<ArtistProfile> existingArtistProfile = artistProfileRepo.findById(id);
        if (existingArtistProfile.isPresent()) {
            try {
                artistProfileRepo.deleteById(id);
                return ResponseEntity.ok().build();
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
