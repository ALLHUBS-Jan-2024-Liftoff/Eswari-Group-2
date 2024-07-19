package org.launchcode.git_artsy_backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Artist extends User{
    @Id
    @GeneratedValue
    private Long artist_id;

    private String profile_picture_url;

    private String bio;

    private String website;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    //Initiates artist_id count

    public Artist() {}

    //Artist constructor
    public Artist(String username, String email, String password, Long artist_id, String profile_picture_url, String bio, String website, LocalDateTime created_at, LocalDateTime updated_at) {
        super(username, email, password);
        this.artist_id = artist_id;
        this.profile_picture_url = profile_picture_url;
        this.bio = bio;
        this.website = website;
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
    }


    //Getters and setter for above variables

    public Long getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(Long artist_id) {
        this.artist_id = artist_id;
    }

    public String getProfile_picture_url() {
        return profile_picture_url;
    }

    public void setProfile_picture_url(String profile_picture_url) {
        this.profile_picture_url = profile_picture_url;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}
