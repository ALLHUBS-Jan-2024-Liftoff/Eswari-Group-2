package org.launchcode.git_artsy_backend.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class FollowArtist {

    @Id
    private Long user_id;
    private Long followed_user_id;

    public FollowArtist(Long user_id, Long followed_user_id) {
        this.user_id = user_id;
        this.followed_user_id = followed_user_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getFollowed_user_id() {
        return followed_user_id;
    }

    public void setFollowed_user_id(Long followed_user_id) {
        this.followed_user_id = followed_user_id;
    }
}
