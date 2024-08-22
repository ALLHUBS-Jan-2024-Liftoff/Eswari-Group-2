package org.launchcode.git_artsy_backend.controllers;

import org.launchcode.git_artsy_backend.models.Artworks;
import org.launchcode.git_artsy_backend.models.Likes;
import org.launchcode.git_artsy_backend.repositories.ArtworksRepo;
import org.launchcode.git_artsy_backend.repositories.LikeRepo;
import org.launchcode.git_artsy_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/like")
@CrossOrigin(origins = "http://localhost:5173")
public class LikeController {

    // Repositories used to interact with the database
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArtworksRepo artworksRepo;

    @Autowired
    private LikeRepo likeRepo;

//     Endpoint to like an artwork.
//     If the user has not already liked the artwork, it saves the like.

    @PostMapping("/like")
    public ResponseEntity<String> like(@RequestParam Long userId, @RequestParam Integer artworkId) {
        Artworks artwork = artworksRepo.getById(artworkId);
        Integer likedArtworkId = artwork.getProductId();

        // Check if the user has already liked this artwork
        if (!likeRepo.existsByUserIdAndLikedArtworkId(userId, likedArtworkId)) {
            Likes like = new Likes(userId, likedArtworkId);
            likeRepo.save(like);  // Save the like to the database
            return ResponseEntity.ok("Artwork Liked");
        }

        // If the artwork is already liked by the user, return a conflict status
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Artwork is already liked");
    }

//    Endpoint to unlike an artwork.
//     If a like exists, it deletes the like.

    @DeleteMapping("/unlike")
    public ResponseEntity<String> unlike(@RequestParam Long userId, @RequestParam Integer artworkId) {
        Artworks artwork = artworksRepo.getById(artworkId);
        Integer likedArtworkId = artwork.getProductId();
        Optional<Likes> like = likeRepo.findByUserIdAndLikedArtworkId(userId, likedArtworkId);

        if (like.isPresent()) {
            likeRepo.delete(like.get());  // Delete the like from the database
            return ResponseEntity.noContent().build();  // Return 204 No Content
        } else {
            // If the like relationship does not exist, return a 404 status
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Like relationship not found");
        }
    }

    //Endpoint to check if a user has liked an artwork.
     //Returns a JSON response with the key "isLiked" and a boolean value.

    @GetMapping("/status")
    public ResponseEntity<Map<String, Boolean>> isArtworkLiked(@RequestParam Long userId, @RequestParam Integer artworkId) {
        Artworks artwork = artworksRepo.getById(artworkId);
        Integer likedArtworkId = artwork.getProductId();
        boolean isLiked = likeRepo.existsByUserIdAndLikedArtworkId(userId, likedArtworkId);

        // Create a response map to send back the like status
        Map<String, Boolean> response = new HashMap<>();
        response.put("isLiked", isLiked);

        return ResponseEntity.ok(response);  // Return the like status
    }
}
