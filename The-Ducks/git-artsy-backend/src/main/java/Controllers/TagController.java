package Controllers;

package org.launchcode.git_artsy_backend.Repositories


import org.launchcode.git_artsy_backend.Tag;

import java.util.List;
import java.util.Optional;

public class TagController {

    private final TagRepository tagRepository;

    // Constructor to inject the data.TagRepository dependency
    public TagController(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    // Get all tags
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    // Get a tag by its ID
    public Tag getTagById(Long id) {
        Optional<Tag> tag = tagRepository.findById(id);
        return tag.orElse(null); // Return null if tag not found
    }

    // Create a new tag
    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    // Update an existing tag
    public Tag updateTag(Long id, Tag tag) {
        if (tagRepository.existsById(id)) {
            tag.setTagId(id);
            return tagRepository.save(tag);
        } else {
            return null; // Return null if tag with given ID does not exist
        }
    }

    // Delete a tag by its ID
    public void deleteTag(Long id) {
        if (tagRepository.existsById(id)) {
            tagRepository.deleteById(id);
        }
    }
}

