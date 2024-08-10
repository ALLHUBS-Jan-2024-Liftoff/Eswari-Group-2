package org.launchcode.git_artsy_backend.models.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class RegisterDTO{

//    @NotEmpty
//    @NotNull
//    @Size(min = 3, max = 15, message = "Invalid username. Must be between 3 and 15 characters.")
    private String username;

    //    @NotEmpty
//    @Email
    private String email;

    //    @NotBlank
//    @NotNull
//    @Size(min = 8, max = 25, message = "Invalid password. Must be between 8 and 25 characters.")
    private String password;

//    @NotEmpty
    private String role;

    private String verifyPassword;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
}
