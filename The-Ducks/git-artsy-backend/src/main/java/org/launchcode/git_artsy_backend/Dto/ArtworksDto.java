package org.launchcode.git_artsy_backend.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

public class ArtworksDto {

    private Integer productId;
    //private Artist artist;
    private String title;
    private String description;
    private Float price;
    private String imageUrl;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
