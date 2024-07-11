package org.launchcode.git_artsy_backend;

import jakarta.persistence.Entity;
import org.apache.catalina.User;

//import javax.persistence.Entity;

@Entity
public abstract class Artist extends User {
    private String profilePictureUrl;
    private String bio;
    private String website;

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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}