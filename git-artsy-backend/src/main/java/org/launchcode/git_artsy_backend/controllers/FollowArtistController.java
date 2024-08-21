package org.launchcode.git_artsy_backend.controllers;

import org.launchcode.git_artsy_backend.models.FollowArtist;
import org.launchcode.git_artsy_backend.models.Profile;
import org.launchcode.git_artsy_backend.repositories.FollowArtistRepository;
import org.launchcode.git_artsy_backend.repositories.ProfileRepo;
import org.launchcode.git_artsy_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/follow")
@CrossOrigin(origins = "http://localhost:5173")
public class FollowArtistController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepo profileRepository;

    @Autowired
    private FollowArtistRepository followArtistRepository;

    @PostMapping("/follow")
    public ResponseEntity<String> follow(@RequestParam Long userId, @RequestParam Integer profileId) {
        Profile profile = profileRepository.getById(profileId);
        Long followedUserId = profile.getUser().getUser_id();
        FollowArtist follow = new FollowArtist(userId, followedUserId);
        followArtistRepository.save(follow);

        return ResponseEntity.ok("Artist Followed");
    }

    @PostMapping("/unfollow")
    public ResponseEntity<String> unfollow(@RequestParam Long userId, @RequestParam int profileId) {
        Profile profile = profileRepository.getById(profileId);
        Long followedUserId = profile.getUser().getUser_id();
        Optional<FollowArtist> follow = followArtistRepository.findByUserIdAndFollowedUserId(userId, followedUserId);
        if (follow.isPresent()) {
            followArtistRepository.delete(follow.get());
            return ResponseEntity.ok("Artist Unfollowed");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Follow relationship not found");
        }
    }

    @GetMapping("/status")
    public ResponseEntity<Map<String, Boolean>> isUserFollowed(@RequestParam Long userId, @RequestParam int profileId) {
        Profile profile = profileRepository.getById(profileId);
        Long followedUserId = profile.getUser().getUser_id();
        System.out.println("Profile ID: " + profileId);
        System.out.println("User ID: " + userId);
        boolean isFollowing = followArtistRepository.existsByUserIdAndFollowedUserId(userId, followedUserId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isFollowing", isFollowing);
        return ResponseEntity.ok(response);
    }
}
