package org.launchcode.git_artsy_backend.controllers;

import org.launchcode.git_artsy_backend.models.FollowArtist;
import org.launchcode.git_artsy_backend.models.User;
import org.launchcode.git_artsy_backend.repositories.FollowArtistRepository;
import org.launchcode.git_artsy_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/follow")
public class FollowArtistController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowArtistRepository followArtistRepository;

    @PostMapping("/follow")
    public ResponseEntity<String> follow(@RequestParam Long user_id, @RequestParam Long followed_user_id) {
        FollowArtist follow = new FollowArtist(user_id, followed_user_id);
        followArtistRepository.save(follow);

        return ResponseEntity.ok("Artist Followed");
    }
//
//    @GetMapping("/unfollow")
//    public ResponseEntity<String> unfollow(Long follow_id) {
//        User followed = userRepository.findById(follow_id)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        Optional<FollowArtist> follow = followArtistRepository.findByUser(followed);
//        followArtistRepository.delete(follow);
//
//        return ResponseEntity.ok("Artist unfollowed");
//    }
}
