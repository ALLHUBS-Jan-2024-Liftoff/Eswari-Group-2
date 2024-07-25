package org.launchcode.git_artsy_backend.controllers;

import org.launchcode.git_artsy_backend.models.Artworks;
import org.launchcode.git_artsy_backend.models.User;
import org.launchcode.git_artsy_backend.models.dto.ArtworksDto;
import org.launchcode.git_artsy_backend.repositories.ArtworksRepo;
import org.launchcode.git_artsy_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/artworks")
@CrossOrigin(origins = "http://localhost:8082")
public class ArtworksController {

    @Autowired
    private ArtworksRepo artworkRepo;

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/new")
    public ResponseEntity<Artworks> createArtwork(@RequestBody ArtworksDto artworkDTO) {
        Optional<User> userOptional = userRepo.findById(artworkDTO.getUserId());
        if (userOptional.isPresent()) {
            Artworks artwork = new Artworks();
            artwork.setUser(userOptional.get());
            artwork.setTitle(artworkDTO.getTitle());
            artwork.setDescription(artworkDTO.getDescription());
            artwork.setPrice(artworkDTO.getPrice());
            artwork.setImageUrl(artworkDTO.getImageUrl());
            artwork.setCreatedAt(LocalDateTime.now());
            artwork.setUpdatedAt(LocalDateTime.now());
            try {
                Artworks savedArtwork = artworkRepo.save(artwork);
                return ResponseEntity.status(HttpStatus.CREATED).body(savedArtwork);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/all")
    public List<Artworks> getAllArtworks() {
        return artworkRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artworks> getArtworkById(@PathVariable Integer id) {
        Optional<Artworks> optionalArtwork = artworkRepo.findById(id);

        if (optionalArtwork.isPresent()) {
            return ResponseEntity.ok(optionalArtwork.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artworks> updateArtwork(@PathVariable Integer id, @RequestBody Artworks updatedArtwork) {
        Optional<Artworks> existingArtwork = artworkRepo.findById(id);

        if (existingArtwork.isPresent()) {
            Artworks artworkToUpdate = existingArtwork.get();
            artworkToUpdate.setTitle(updatedArtwork.getTitle());
            artworkToUpdate.setDescription(updatedArtwork.getDescription());
            artworkToUpdate.setPrice(updatedArtwork.getPrice());
            artworkToUpdate.setImageUrl(updatedArtwork.getImageUrl());
            artworkToUpdate.setUpdatedAt(LocalDateTime.now());
            try {
                Artworks savedArtwork = artworkRepo.save(artworkToUpdate);
                return ResponseEntity.ok(savedArtwork);
            } catch (Exception e) {
                // Handle database or save errors
                e.printStackTrace(); // Log the exception
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtwork(@PathVariable Integer id) {
        Optional<Artworks> existingArtwork = artworkRepo.findById(id);

        if (existingArtwork.isPresent()) {
            try {
                artworkRepo.deleteById(id);
                return ResponseEntity.ok().build();
            } catch (Exception e) {
                // Handle database or delete errors
                e.printStackTrace(); // Log the exception
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            // Artwork with given ID not found
            return ResponseEntity.notFound().build();
        }
    }
}
