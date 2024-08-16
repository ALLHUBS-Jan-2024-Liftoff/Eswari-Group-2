package org.launchcode.git_artsy_backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long user_id;

    @NotEmpty
    @NotNull
//    @Size(min = 3, max = 15, message = "Invalid username. Must be between 3 and 15 characters.")
    private String username;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @NotNull
//    @Size(min = 8, max = 25, message = "Invalid password. Must be between 8 and 25 characters.")
    private String password;

    @NotEmpty
    private String role;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    //    Identifies the password encoder
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @OneToOne(mappedBy = "user")
    private Profile profile;

    //Initiates user_id count
    public User() {}

    //Constructor
    public User(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = encoder.encode(password);
        this.role = role;
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
    }


    //Getters and setters for above variables
    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, this.password);
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    //    equals, hashcode, and toString methods if needed
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(user_id, user.user_id) && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && role == user.role && Objects.equals(created_at, user.created_at) && Objects.equals(updated_at, user.updated_at) && Objects.equals(profile, user.profile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, username, email, password, role, created_at, updated_at, profile);
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", createdAt=" + created_at +
                ", updatedAt=" + updated_at +
                ", profile=" + profile +
                '}';
    }
}