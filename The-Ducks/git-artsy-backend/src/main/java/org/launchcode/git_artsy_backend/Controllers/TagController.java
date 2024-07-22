package org.launchcode.git_artsy_backend.Controllers;

import org.launchcode.git_artsy_backend.Models.Tag;

import java.util.ArrayList;
import java.util.List;

//Controller for managing Tags.

public class TagController {

    // List to store tags in memory (replace with database logic in a real application)
    private List<Tag> tags = new ArrayList<>();

    //Retrieves all tags.

    public List<Tag> getAllTags() {
        return tags;
    }

    // Retrieves a tag by its ID.

    public Tag getTagById(Long id) {
        for (Tag tag : tags) {
            if (tag.getTagId().equals(id)) {
                return tag;
            }
        }
        return null;
    }

    //Creates a new tag.

    public Tag createTag(Tag tag) {
        tags.add(tag);
        return tag;
    }

    //Updates an existing tag.

    public Tag updateTag(Long id, Tag newTag) {
        for (Tag tag : tags) {
            if (tag.getTagId().equals(id)) {
                tag.setName(newTag.getName());
                return tag;
            }
        }
        return null;
    }

    // Deletes a tag by its ID.
    
    boolean deleteTag(Long id) {

        return tags.removeIf(tag -> tag.getTagId().equals(id));
    }
}
