package org.launchcode.git_artsy_backend;

import jakarta.persistence.*;

import java.sql.Timestamp;

/**
 * Represents a Product (or Artwork) in the application.
 */

@Entity
public class Product {
    // Primary key for the Product table
    @Id
    private Long productId;

    // Many-to-one relationship with the Artist table
    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;

    // Title column, not null
    @Column(nullable = false)
    private String title;

    // Description column
    @Column
    private String description;

    // Price column, not null
    @Column(nullable = false)
    private Double price;

    // URL for the product image
    @Column
    private String imageUrl;

    // Timestamp when the product is created, not updatable
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    // Timestamp when the product is last updated
    @Column(nullable = false)
    private Timestamp updatedAt;

    // Method to set createdAt before persisting the product
    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }

    // Method to update updatedAt before updating the product
    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }

    public Product(Long productId, Artist artist, String title, String description, Double price, String imageUrl, Timestamp createdAt, Timestamp updatedAt) {
        this.productId = productId;
        this.artist = artist;
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // No-argument constructor
    public Product() {}

    // Getters and setters for all fields
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
