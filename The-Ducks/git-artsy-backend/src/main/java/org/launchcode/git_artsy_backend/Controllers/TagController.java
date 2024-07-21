package org.launchcode.git_artsy_backend.Controllers;

import org.launchcode.git_artsy_backend.Models.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for managing Tags.
 * This class provides methods to handle HTTP requests for creating, reading, updating, and deleting tags.
 */
public class TagController {

    // List to store tags in memory (replace with database logic in a real application)
    private List<Tag> tags = new ArrayList<>();

    /**
     * Retrieves all tags.
     * @return a list of all tags.
     */
    public List<Tag> getAllTags() {
        return tags;
    }

    /**
     * Retrieves a tag by its ID.
     * @param id the ID of the tag to retrieve.
     * @return the tag with the specified ID, or null if not found.
     */
    public Tag getTagById(Long id) {
        for (Tag tag : tags) {
            if (tag.getTagId().equals(id)) {
                return tag;
            }
        }
        return null;
    }

    /**
     * Creates a new tag.
     * @param tag the tag to create.
     * @return the created tag.
     */
    public Tag createTag(Tag tag) {
        tags.add(tag);
        return tag;
    }

    /**
     * Updates an existing tag.
     * @param id the ID of the tag to update.
     * @param newTag the new tag data.
     * @return the updated tag, or null if not found.
     */
    public Tag updateTag(Long id, Tag newTag) {
        for (Tag tag : tags) {
            if (tag.getTagId().equals(id)) {
                tag.setName(newTag.getName());
                return tag;
            }
        }
        return null;
    }

    /**
     * Deletes a tag by its ID.
     * @param id the ID of the tag to delete.
     * @return true if the tag was deleted, false if not found.
     */
    public boolean deleteTag(Long id) {
        return tags.removeIf(tag -> tag.getTagId().equals(id));
    }
}
