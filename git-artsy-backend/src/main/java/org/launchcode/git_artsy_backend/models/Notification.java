package org.launchcode.git_artsy_backend.models;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity

public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "artwork_id", nullable = false)
    private Artworks artwork;

    private boolean isSaw;

    private LocalDateTime readAt;

    private LocalDateTime createdAt;

    // Default constructor
    public Notification() {
        this.createdAt = LocalDateTime.now();
    }

    // Constructor with parameters
    public Notification(User user, Artworks artwork) {
        this.user = user;
        this.artwork = artwork;
        this.isSaw = false;
        this.readAt = null;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Artworks getArtwork() {
        return artwork;
    }

    public void setArtwork(Artworks artwork) {
        this.artwork = artwork;
    }

    public boolean isRead() {
        return isSaw;
    }

    public void setRead(boolean isRead) {
        this.isSaw = isRead;
        if (isRead) {
            this.readAt = LocalDateTime.now();
        }
    }

    public LocalDateTime getReadAt() {
        return readAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @PreUpdate
    public void preUpdate() {
        this.createdAt = LocalDateTime.now();
    }
}
