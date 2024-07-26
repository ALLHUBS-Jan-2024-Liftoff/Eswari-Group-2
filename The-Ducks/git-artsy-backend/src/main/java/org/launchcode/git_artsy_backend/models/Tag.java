package org.launchcode.git_artsy_backend.models;

//This class contains fields for the tag's ID and name.
// It will be used to tag products/artworks for categorization or searching purposes.

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tag {

    // Unique identifier for each tag
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    // Name of the tag
    private String name;

    // Default constructor
    public Tag() {
    }

    // Parameterized constructor to initialize tag fields
    public Tag(Long tagId, String name) {
        this.tagId = tagId;
        this.name = name;
    }

    // Getter for tagId
    public Long getTagId() {

        return tagId;
    }

    // Setter for tagId
    public void setTagId(Long tagId) {

        this.tagId = tagId;
    }

    // Getter for name
    public String getName() {

        return name;
    }

    // Setter for name
    public void setName(String name) {

        this.name = name;
    }
}
