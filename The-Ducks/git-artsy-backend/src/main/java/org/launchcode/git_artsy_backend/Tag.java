package org.launchcode.git_artsy_backend;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;



/**
 * Represents a Tag in the application.
 * Tags are used to categorize products.
 */
@Entity
public class Tag {
    // Primary key for the Tag table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    // Name of the tag
    @Column(nullable = false)
    private String name;

    public Tag(Long tagId, String name) {
        this.tagId = tagId;
        this.name = name;
    }

    //No arg const
    public Tag() {}

    // Getters and setters for all fields
    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

