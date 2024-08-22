package org.launchcode.git_artsy_backend.repositories;

import org.launchcode.git_artsy_backend.models.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepo extends JpaRepository<Likes, Long> {
    // This method finds a Like entity by userId and likedArtworkId
    Optional<Likes> findByUserIdAndLikedArtworkId(Long userId, Integer likedArtworkId);

    // This method checks if a Like entity exists for a given userId and likedArtworkId
    boolean existsByUserIdAndLikedArtworkId(Long userId, Integer likedArtworkId);
}

