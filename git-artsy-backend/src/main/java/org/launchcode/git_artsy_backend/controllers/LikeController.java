package org.launchcode.git_artsy_backend.controllers;

import org.launchcode.git_artsy_backend.models.Artworks;
import org.launchcode.git_artsy_backend.models.Like;
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
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArtworksRepo artworksRepo;

    @Autowired
    private LikeRepo likeRepo;

    @PostMapping("/like")
    public ResponseEntity<String> like(@RequestParam Long userId, @RequestParam Integer artworkId) {
        Artworks artwork = artworksRepo.getById(artworkId);
        Integer likedArtworkId = artwork.getProductId(); //gets the id of the artwork
        Like like = new Like(userId, likedArtworkId);
        likeRepo.save(like);

        return ResponseEntity.ok("Artwork Liked");
    }

    @PostMapping("/unlike")
    public ResponseEntity<String> unlike(@RequestParam Long userId, @RequestParam int artworkId) {
        Artworks artwork = artworksRepo.getById(artworkId);
        Integer likedArtworkId = artwork.getProductId();
        Optional<Like> like = likeRepo.findByUserIdAndLikedArtworkId(userId, likedArtworkId);
        if (like.isPresent()) {
            likeRepo.delete(like.get());
            return ResponseEntity.ok("Artwork Unliked");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Like relationship not found");
        }
    }

    @GetMapping("/status")
    public ResponseEntity<Map<String, Boolean>> isArtworkLiked(@RequestParam Long userId, @RequestParam int artworkId) {
        Artworks artwork = artworksRepo.getById(artworkId);
        Integer likedArtworkId = artwork.getProductId();
        boolean isLiked = likeRepo.existsByUserIdAndFollowedUserId(userId, artworkId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isFollowing", isLiked);
        return ResponseEntity.ok(response);
    }

}
