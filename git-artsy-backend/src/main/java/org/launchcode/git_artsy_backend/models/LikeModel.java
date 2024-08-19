package org.launchcode.git_artsy_backend.models;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class LikeModel {
    //like identifier
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne
    @JoinColumn(name = "artwork_id", nullable = false)
    private Artworks artworks;

    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    // Method to set createdAt before persisting the like
    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }

    public LikeModel(Long likeId, Artworks artworks, Timestamp createdAt) {
        this.likeId = likeId;
        this.artworks = artworks;
        this.createdAt = createdAt;
    }

    public Long getLikeId() {
        return likeId;
    }

    public void setLikeId(Long likeId) {
        this.likeId = likeId;
    }

    public Artworks getArtworks() {
        return artworks;
    }

    public void setArtworks(Artworks artworks) {
        this.artworks = artworks;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
