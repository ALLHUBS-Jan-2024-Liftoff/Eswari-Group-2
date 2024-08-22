package org.launchcode.git_artsy_backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Like {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "liked_artwork_id")
    private Long likedArtworkId;

    public Like(Long userId, Integer likedArtworkId) {
        this.userId = userId;
        this.likedArtworkId = likedArtworkId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getLikedArtworkId() {
        return likedArtworkId;
    }

    public void setLikedArtworkId(Long likedArtworkId) {
        this.likedArtworkId = likedArtworkId;
    }
}
