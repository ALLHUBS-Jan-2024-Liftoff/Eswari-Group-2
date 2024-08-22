package org.launchcode.git_artsy_backend.controllers;

import org.launchcode.git_artsy_backend.models.Artworks;

import org.launchcode.git_artsy_backend.models.Likes;
import org.launchcode.git_artsy_backend.repositories.ArtworksRepo;

import org.launchcode.git_artsy_backend.repositories.LikesRepo;
import org.launchcode.git_artsy_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/likes")
@CrossOrigin(origins = "http://localhost:5173")
public class LikesController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArtworksRepo artworksRepo;

    @Autowired
    private LikesRepo likeRepo;

    @PostMapping("/like")
    public ResponseEntity<String> like(@RequestParam Long userId, @RequestParam Integer artworkId) {
        Artworks artwork = artworksRepo.getById(artworkId);
        Integer likedArtworkId = artwork.getProductId(); //gets the id of the artwork
        Likes like = new Likes(userId, likedArtworkId);
        likeRepo.save(like);

        return ResponseEntity.ok("Artwork Liked");
    }

    @PostMapping("/unlike")
    public ResponseEntity<String> unlike(@RequestParam Long userId, @RequestParam Integer artworkId) {
        Artworks artwork = artworksRepo.getById(artworkId);
        Integer likedArtworkId = artwork.getProductId();
        Optional<Likes> like = likeRepo.findByUserIdAndLikedArtworkId(userId, likedArtworkId);
        if (like.isPresent()) {
            likeRepo.delete(like.get());
            return ResponseEntity.ok("Artwork Unliked");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Like relationship not found");
        }
    }

    @GetMapping("/status")
    public ResponseEntity<Map<String, Boolean>> isArtworkLiked(@RequestParam Long userId, @RequestParam Integer artworkId) {
        Artworks artwork = artworksRepo.getById(artworkId);
        Integer likedArtworkId = artwork.getProductId();
        boolean isLiked = likeRepo.existsByUserIdAndLikedArtworkId(userId, likedArtworkId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isLiked", isLiked);
        return ResponseEntity.ok(response);
    }

}
