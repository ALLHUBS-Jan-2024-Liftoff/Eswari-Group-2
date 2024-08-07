package org.launchcode.git_artsy_backend.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

//This class contains fields for the tag's ID and name.
// It will be used to tag products/artworks for categorization or searching purposes.
@Entity
@Table(name = "tags")
public class Tag {

    // Unique identifier for each tag
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    // Name of the tag
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Artworks> artworks = new ArrayList<>();

    // Default constructor
    public Tag(String tagName) {
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

    public List<Artworks> getArtworks() {
        return artworks;
    }

    public void setArtworks(List<Artworks> artworks) {
        this.artworks = artworks;
    }
}
