package org.launchcode.git_artsy_backend.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

//Represents a commission request in the application.
@Entity
public class PatronCommissionRequest {

    @Id
    private Long id;

    //Should I keep fromUserId, toUserId, requestt?
    private Long fromUserId;
    private Long toUserId;
    //private String request;

    private String detail;
    private String description;
    private String subject;

    // Constructors
    public PatronCommissionRequest() {}

    public PatronCommissionRequest(String detail, String description, String subject, Long fromUserId, Long toUserId, String request) {
        this.detail = detail;
        this.description = description;
        this.subject = subject;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        ///this.request = request;
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

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

//    public String getRequest() {
//        return request;
//    }
//
//    public void setRequest(String request) {
//        this.request = request;
//    }
}