package org.launchcode.git_artsy_backend.repositories;

import org.launchcode.git_artsy_backend.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepo extends JpaRepository<Like, Long> {

    Optional<Like> findByUserIdAndLikedArtworkId(Long userId, Integer likedArtworkId);

    boolean existsByUserIdAndFollowedUserId(Long userId, int artworkId);
}
