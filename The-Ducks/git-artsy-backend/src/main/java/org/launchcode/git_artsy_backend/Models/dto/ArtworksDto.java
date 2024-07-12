package org.launchcode.git_artsy_backend.Models.dto;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class ArtworksDto {
    private Integer productId;
    //private Artist artist;
    private String title;
    private String description;
    private Float price;
    private String imageUrl;


    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
