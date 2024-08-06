package org.launchcode.git_artsy_backend.models.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public class ArtworksDto {
    private MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }
    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
