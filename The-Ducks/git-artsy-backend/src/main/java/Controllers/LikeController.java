package Controllers;

import org.launchcode.git_artsy_backend.Like;

import java.util.List;

public class LikeController {

    private final LikeRepository likeRepository;

    // Constructor to inject the data.LikeRepository dependency
    public LikeController(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    // Get all likes
    public List<Like> getAllLikes() {
        return likeRepository.findAll();
    }

    // Get a like by its ID
    public Like getLikeById(Long id) {
        return likeRepository.findById(id).orElse(null);
    }

    // Create a new like
    public Like createLike(Like like) {
        return likeRepository.save(like);
    }

    // Delete a like by its ID
    public void deleteLike(Long id) {
        likeRepository.deleteById(id);
    }
}

