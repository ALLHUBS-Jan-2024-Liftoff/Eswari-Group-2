package org.launchcode.git_artsy_backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class User implements UserDetails {

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

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @NotEmpty
    private String role;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

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
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }


    //Getters and setters for above variables

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
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

    public void setPassword(String password) {
        this.password = password;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User user = (User) o;
//        return Objects.equals(user_id, user.user_id) && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(hashedPassword, user.hashedPassword) && Objects.equals(role, user.role) && Objects.equals(createdAt, user.createdAt) && Objects.equals(updatedAt, user.updatedAt) && Objects.equals(profile, user.profile);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(user_id, username, email, hashedPassword, role, createdAt, updatedAt, profile);
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "user_id=" + user_id +
//                ", username='" + username + '\'' +
//                ", email='" + email + '\'' +
//                ", hashedPassword='" + hashedPassword + '\'' +
//                ", role='" + role + '\'' +
//                ", createdAt=" + createdAt +
//                ", updatedAt=" + updatedAt +
//                ", profile=" + profile +
//                '}';
//    }
}