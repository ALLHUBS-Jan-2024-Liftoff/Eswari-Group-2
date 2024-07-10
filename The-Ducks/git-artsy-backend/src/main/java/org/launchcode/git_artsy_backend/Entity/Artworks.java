package org.launchcode.git_artsy_backend.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Artworks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

//    @ManyToOne
//    @JoinColumn(name = "artist_id", nullable = false)
//    private Artist artist;

    private String title;
    private String description;
    private Float price;
    private String imageUrl;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Artworks() {}

    public Artworks( String title, String description, Float price, String imageUrl) {
        //Artists artist
        //this.artist = artist;

        this.title = title;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
//
//    public Artist getArtist() {
//        return artist;
//    }
//
//    public void setArtist(Artist artist) {
//        this.artist = artist;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
}
