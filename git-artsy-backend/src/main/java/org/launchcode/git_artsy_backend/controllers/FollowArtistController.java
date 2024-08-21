package org.launchcode.git_artsy_backend.controllers;

import org.launchcode.git_artsy_backend.models.FollowArtist;
import org.launchcode.git_artsy_backend.models.Profile;
import org.launchcode.git_artsy_backend.models.User;
import org.launchcode.git_artsy_backend.repositories.FollowArtistRepository;
import org.launchcode.git_artsy_backend.repositories.ProfileRepo;
import org.launchcode.git_artsy_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

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
        Long followedUserId = profile.getUser().getUser_id(); //gets the userId the user's profile that we are on
        FollowArtist follow = new FollowArtist(userId, followedUserId);
        followArtistRepository.save(follow);

        return ResponseEntity.ok("Artist Followed");
    }

    @PostMapping("/unfollow")
    public ResponseEntity<String> unfollow(@RequestParam Long userId, @RequestParam int profileId) {
        Profile profile = profileRepository.getById(profileId);
        Long followedUserId = profile.getUser().getUser_id(); //gets the userId the user's profile that we are on
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
        Long followedUserId = profile.getUser().getUser_id(); //gets the userId the user's profile that we are on
        boolean isFollowing = followArtistRepository.existsByUserIdAndFollowedUserId(userId, followedUserId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isFollowing", isFollowing);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/following")
    public ResponseEntity<List<String>> getFollowingArtistNames(@RequestParam Long userId) {
        // Get the list of followed user IDs for the given user ID
        List<FollowArtist> followList = followArtistRepository.findByUserId(userId);

        // Initialize a list to hold the artist names
        List<String> artistNames = new ArrayList<>();

        // Loop through the followList to find each followed artist's name
        for (FollowArtist follow : followList) {
            // Find the User object for the followed user ID
            Optional<User> followedUserOptional = userRepository.findById(follow.getFollowedUserId());

            if (followedUserOptional.isPresent()) {
                // Find the Profile associated with the followed User
                Optional<Profile> profileOptional = profileRepository.findByUser(followedUserOptional.get());

                if (profileOptional.isPresent()) {
                    // If the profile exists, add the  name to the artistNames list
                    artistNames.add(profileOptional.get().getName());
                } else {
                    // If the profile does not exist, add "Unknown Artist" to the list
                    artistNames.add("Unknown Artist");
                }
            } else {
                // If the user does not exist, add "Unknown Artist" to the list
                artistNames.add("Unknown Artist");
            }
        }

        // Return the list of artist names as a response
        return ResponseEntity.ok(artistNames);
    }


}
