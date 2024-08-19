package org.launchcode.git_artsy_backend.controllers;

import org.launchcode.git_artsy_backend.models.LikeModel;
import org.launchcode.git_artsy_backend.repositories.LikeRepository;

import java.util.List;

public class LikeController {
    private final LikeRepository likeRepository;

    // Constructor to inject the data.LikeRepository dependency
    public LikeController(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    // Get all likes
    public List<LikeModel> getAllLikes() {
        return likeRepository.findAll();
    }

    // Get a like by its ID
    public LikeModel getLikeById(Long id) {
        return likeRepository.findById(id).orElse(null);
    }

    // Create a new like
    public LikeModel createLike(LikeModel like) {
        return likeRepository.save(like);
    }

    // Delete a like by its ID
    public void deleteLike(Long id) {
        likeRepository.deleteById(id);
    }
}
