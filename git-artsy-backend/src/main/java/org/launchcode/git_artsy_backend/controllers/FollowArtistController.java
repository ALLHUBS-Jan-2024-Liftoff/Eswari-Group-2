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
    public ResponseEntity<List<Map<String, Object>>> getFollowingArtistNames(@RequestParam Long userId) {
        // get the list of followed user
        List<FollowArtist> followList = followArtistRepository.findByUserId(userId);

        // initialize a list to save artist names and profile IDs
        List<Map<String, Object>> artistProfiles = new ArrayList<>();

        // loop through the followList to find each followed artist's name and profile ID
        for (FollowArtist follow : followList) {
            // find the User from userrepo
            Optional<User> followedUserOptional = userRepository.findById(follow.getFollowedUserId());

            if (followedUserOptional.isPresent()) {
                // the Profile associated with the User
                Optional<Profile> profileOptional = profileRepository.findByUser(followedUserOptional.get());

                Map<String, Object> artistData = new HashMap<>();
                if (profileOptional.isPresent()) {
                    // if the profile exists, add the name and profile ID to the map
                    artistData.put("name", profileOptional.get().getName());
                    artistData.put("profileId", profileOptional.get().getId());
                } else {

                    artistData.put("name", "Unknown Artist");
                    artistData.put("profileId", null);
                }

                artistProfiles.add(artistData);
            } else {

                Map<String, Object> unknownArtist = new HashMap<>();
                unknownArtist.put("name", "Unknown Artist");
                unknownArtist.put("profileId", null);
                artistProfiles.add(unknownArtist);
            }
        }

        // Return the list of artist profiles as a response
        return ResponseEntity.ok(artistProfiles);
    }



}
