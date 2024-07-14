package org.launchcode.git_artsy_backend;
import jakarta.persistence.*;

import java.sql.Timestamp;

/**
 * Represents a Like in the application.
 * A Like is associated with a Patron and a Product.
 */

@Entity
public class Like {
    // Primary key for the Like table
    @Id
    private Long likeId;

    // Many-to-one relationship with the Patron table
    @ManyToOne
    @JoinColumn(name = "patron_id", nullable = false)
    private Patron patron;

    // Many-to-one relationship with the Product table
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // Timestamp when the like is created, not updatable
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    // Method to set createdAt before persisting the like
    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }

    // Getters and setters for all fields
    public Long getLikeId() {
        return likeId;
    }

    public void setLikeId(Long likeId) {
        this.likeId = likeId;
    }

    public Patron getPatron() {
        return patron;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}

