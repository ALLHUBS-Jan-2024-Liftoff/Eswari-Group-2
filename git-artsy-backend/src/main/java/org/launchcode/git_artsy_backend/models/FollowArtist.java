package org.launchcode.git_artsy_backend.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.ArrayList;
import java.util.List;

public class FollowArtist {

    @Id
    @GeneratedValue
    private Long follow_id;

    @ManyToOne
    private List<User> artistsFollowing = new ArrayList<>();

    public FollowArtist() {}

    public FollowArtist(Long follow_id, List<User> artistsFollowing) {
        this.follow_id = follow_id;
        this.artistsFollowing = artistsFollowing;
    }

    public Long getFollow_id() {
        return follow_id;
    }

    public void setFollow_id(Long follow_id) {
        this.follow_id = follow_id;
    }

    public List<User> getArtistsFollowing() {
        return artistsFollowing;
    }

    public void setArtistsFollowing(List<User> artistsFollowing) {
        this.artistsFollowing = artistsFollowing;
    }
}
