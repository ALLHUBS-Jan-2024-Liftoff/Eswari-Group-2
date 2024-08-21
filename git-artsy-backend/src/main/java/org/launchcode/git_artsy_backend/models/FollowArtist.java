package org.launchcode.git_artsy_backend.models;

import jakarta.persistence.*;

@Entity
public class FollowArtist {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "followed_user_id")
    private Long followedUserId;

    public FollowArtist(Long userId, Long followedUserId) {
        this.userId = userId;
        this.followedUserId = followedUserId;
    }

    public FollowArtist() {}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFollowedUserId() {
        return followedUserId;
    }

    public void setFollowedUserId(Long followedUserId) {
        this.followedUserId = followedUserId;
    }
}
