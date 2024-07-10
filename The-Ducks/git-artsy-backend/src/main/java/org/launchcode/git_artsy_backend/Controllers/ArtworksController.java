package org.launchcode.git_artsy_backend.Controllers;


import org.launchcode.git_artsy_backend.Entity.Artworks;
import org.launchcode.git_artsy_backend.Response.ApiResponse;
import org.launchcode.git_artsy_backend.Service.ArtworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artworks")
@CrossOrigin(origins = "http://localhost:8082")
public class ArtworksController {

    @Autowired
    private ArtworkService artworkService;

    @PostMapping
    public ResponseEntity<Artworks> createArtwork(@RequestBody Artworks artwork) {
        ApiResponse<Artworks> response = artworkService.saveArtwork(artwork);
        return ResponseEntity.ok(response.getData());
    }

    @GetMapping
    public ResponseEntity<List<Artworks>> getAllArtworks() {
        ApiResponse<List<Artworks>> response = artworkService.getAllArtworks();
        return ResponseEntity.ok(response.getData());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Optional<Artworks>>> getArtworkById(@PathVariable Integer id) {
        ApiResponse<Optional<Artworks>> response = artworkService.getArtworkById(id);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(404).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artworks> updateArtwork(@PathVariable Integer id, @RequestBody Artworks artwork) {
        ApiResponse<Artworks> response = artworkService.updateArtwork(id, artwork);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response.getData());
        } else {
            return ResponseEntity.status(404).body(response.getData());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtwork(@PathVariable Integer id) {
        ApiResponse<Void> response = artworkService.deleteArtwork(id);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response.getData());
        } else {
            return ResponseEntity.status(404).body(response.getData());
        }
    }
}
