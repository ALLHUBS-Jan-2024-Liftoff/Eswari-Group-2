package org.launchcode.git_artsy_backend.models;

import jakarta.persistence.*;
import java.util.Set;

//Represents an image in the application.

@Entity
public class ImageSearch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String url;
    private String title;
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "image_tag",
            joinColumns = @JoinColumn(name = "image_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;

    // Constructors
    public ImageSearch() {
    }

    public ImageSearch(String url, String title, String description) {
        this.url = url;
        this.title = title;
        this.description = description;
    }


    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}


