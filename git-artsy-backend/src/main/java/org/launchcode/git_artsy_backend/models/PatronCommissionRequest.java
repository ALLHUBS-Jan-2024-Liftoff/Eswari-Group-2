package org.launchcode.git_artsy_backend.models;


import jakarta.persistence.*;

// PatronCommissionRequest.java
@Entity
public class PatronCommissionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Reference to the artist's user ID instead of an email
    @ManyToOne
    @JoinColumn(name = "artist_id",  referencedColumnName = "user_id", nullable = false)
    private User artist;

    // The subject of the commission request.
    private String subject;

    // The type of commission (e.g., Painting, Drawing, Mural).
    private String details;

    // The description of the commission request.
    private String description;

    // Getters and setters


    public PatronCommissionRequest(String subject, String details, String description) {
        this.subject = subject;
        this.details = details;
        this.description = description;
    }

    public PatronCommissionRequest(Long id, User artist, String subject, String details, String description) {
        this.id = id;
        this.artist = artist;
        this.subject = subject;
        this.details = details;
        this.description = description;
    }

    public PatronCommissionRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getArtist() {
        return artist;
    }

    public void setArtist(User artist) {
        this.artist = artist;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}


