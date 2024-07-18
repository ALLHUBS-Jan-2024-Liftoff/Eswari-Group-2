package org.launchcode.git_artsy_backend;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

/**
 * Represents a Subscription in the application.
 * A Subscription is associated with a Patron and an Artist.
 */

@Entity
public class Subscription {
    // Primary key for the Subscription table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscriptionId;

    // Many-to-one relationship with the Patron table
    @ManyToOne
    @JoinColumn(name = "patron_id", nullable = false)
    private Patron patron;

    // Many-to-one relationship with the Artist table
    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;

    // Timestamp when the subscription is created, not updatable
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    // Method to set createdAt before persisting the subscription
    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }

    public Subscription(Long subscriptionId, Patron patron, Artist artist, Timestamp createdAt) {
        this.subscriptionId = subscriptionId;
        this.patron = patron;
        this.artist = artist;
        this.createdAt = createdAt;
    }

    //No arg Const
    public Subscription() {}

    // Getters and setters for all fields
    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Patron getPatron() {
        return patron;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
