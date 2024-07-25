package org.launchcode.git_artsy_backend.models.dto;

import jakarta.persistence.*;

import java.time.LocalDateTime;


public class ProfileDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "profile_pic")
    private String profilePic;

    @Column(name = "bio_description", length = 500)
    private String bioDescription;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public ProfileDto() {
    }

    public ProfileDto( String name, String location, String email, String phone, String profilePic, String bioDescription) {
//        User user,
//        this.user = user;
        this.name = name;
        this.location = location;
        this.email = email;
        this.phone = phone;
        this.profilePic = profilePic;
        this.bioDescription = bioDescription;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getBioDescription() {
        return bioDescription;
    }

    public void setBioDescription(String bioDescription) {
        this.bioDescription = bioDescription;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "ArtistProfile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", profilePic='" + profilePic + '\'' +
                ", bioDescription='" + bioDescription + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}