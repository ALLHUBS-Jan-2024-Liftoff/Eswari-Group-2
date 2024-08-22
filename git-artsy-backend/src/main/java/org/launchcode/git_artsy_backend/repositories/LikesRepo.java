package org.launchcode.git_artsy_backend.repositories;

import org.launchcode.git_artsy_backend.models.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepo extends JpaRepository<Likes, Long> {

    Optional<Likes> findByUserIdAndLikedArtworkId(Long userId, Integer likedArtworkId);

    boolean existsByUserIdAndLikedArtworkId(Long userId, Integer artworkId);
}
