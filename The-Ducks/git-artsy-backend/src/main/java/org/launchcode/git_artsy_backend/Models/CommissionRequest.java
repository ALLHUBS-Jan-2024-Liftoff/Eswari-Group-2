package org.launchcode.git_artsy_backend.Models;


import jakarta.persistence.Entity;

/**
 * Represents a commission request in the application.
 */

@Entity
public class CommissionRequest {

    private Long id;

    private String detail;
    private String description;
    private String subject;

    // Constructors
    public CommissionRequest() {}

    public CommissionRequest(String detail, String description, String subject) {
        this.detail = detail;
        this.description = description;
        this.subject = subject;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
