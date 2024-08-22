package org.launchcode.git_artsy_backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Likes {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "liked_artwork_id")
    private Integer likedArtworkId;

    public Likes(Long userId, Integer likedArtworkId) {
        this.userId = userId;
        this.likedArtworkId = likedArtworkId;
    }

    public Likes(){};

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getLikedArtworkId() {
        return likedArtworkId;
    }

    public void setLikedArtworkId(Integer likedArtworkId) {
        this.likedArtworkId = likedArtworkId;
    }
}
