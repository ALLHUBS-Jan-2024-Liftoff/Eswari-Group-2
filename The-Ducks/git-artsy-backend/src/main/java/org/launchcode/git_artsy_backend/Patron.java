package org.launchcode.git_artsy_backend;

import jakarta.persistence.Entity;

@Entity
public abstract class Patron extends User {
    private String profilePictureUrl;
    private String bio;

    // Getters and setters
    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}