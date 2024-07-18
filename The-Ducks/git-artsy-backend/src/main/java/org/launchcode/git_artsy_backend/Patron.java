package org.launchcode.git_artsy_backend;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

@Entity
public class Patron extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String profilePictureUrl;
    private String bio;

    public Patron(Long userId, String username, String email, String password, String role, Timestamp createdAt, Timestamp updatedAt, String profilePictureUrl, String bio) {
        super(userId, username, email, password, role, createdAt, updatedAt);
        this.profilePictureUrl = profilePictureUrl;
        this.bio = bio;
    }

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