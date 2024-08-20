package org.launchcode.git_artsy_backend.models.dto;

import org.launchcode.git_artsy_backend.models.User;

public class PatronDTO {

    private Long artist_id;

    // The subject of the commission request.
    private String subject;

    // The type of commission (e.g., Painting, Drawing, Mural).
    private String details;

    // The description of the commission request.
    private String description;

    public PatronDTO() {
    }

    public PatronDTO(Long artist_id, String subject, String details, String description) {
        this.artist_id = artist_id;
        this.subject = subject;
        this.details = details;
        this.description = description;
    }

    public Long getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(Long artist_id) {
        this.artist_id = artist_id;
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


