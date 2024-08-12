package org.launchcode.git_artsy_backend.models.dto;

import jakarta.validation.constraints.*;

public class LoginDTO {

    @NotEmpty
    @Email
    private String email;

    @NotBlank
    @NotNull
    @Size(min = 8, max = 25, message = "Invalid password. Must be between 8 and 25 characters.")
    private String password;

    public @NotEmpty @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty @Email String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
