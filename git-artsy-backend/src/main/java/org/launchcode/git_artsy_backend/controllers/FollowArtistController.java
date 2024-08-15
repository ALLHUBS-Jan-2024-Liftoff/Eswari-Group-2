package org.launchcode.git_artsy_backend.controllers;

import org.launchcode.git_artsy_backend.models.FollowArtist;
import org.launchcode.git_artsy_backend.models.User;
import org.launchcode.git_artsy_backend.repositories.FollowArtistRepository;
import org.launchcode.git_artsy_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/follow")
public class FollowArtistController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowArtistRepository followArtistRepository;


    public void followUser(Long follow_id) {
        User followed = userRepository.findById(follow_id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        FollowArtist follow = new FollowArtist();
        follow.setArtistsFollowing((List<User>) followed);

        followArtistRepository.save(follow);
    }

    public void unfollowUser(Long follow_id) {
        User followed = userRepository.findById(follow_id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Optional<FollowArtist> follow = followArtistRepository.findByUser(followed);

        followArtistRepository.delete(follow);
    }
}
