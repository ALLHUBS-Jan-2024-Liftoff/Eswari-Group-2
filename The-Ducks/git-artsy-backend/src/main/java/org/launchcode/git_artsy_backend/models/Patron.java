package org.launchcode.git_artsy_backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Patron extends User {

    @Id
    @GeneratedValue
    private Long patron_id;

    private String profile_picture_url;

    private String bio;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    //Initiates patron_id count

    public Patron() {}

    //Patron constructor
    public Patron(String username, String email, String password, Long patron_id, String profile_picture_url, String bio, LocalDateTime created_at, LocalDateTime updated_at) {
        super(username, email, password);
        this.patron_id = patron_id;
        this.profile_picture_url = profile_picture_url;
        this.bio = bio;
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
    }


    //Getters and setter for above variables

    public Long getPatron_id() {
        return patron_id;
    }

    public void setPatron_id(Long patron_id) {
        this.patron_id = patron_id;
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
