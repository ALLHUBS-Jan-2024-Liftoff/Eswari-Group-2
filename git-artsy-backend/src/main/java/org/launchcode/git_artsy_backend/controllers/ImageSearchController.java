package org.launchcode.git_artsy_backend.controllers;

import org.launchcode.git_artsy_backend.models.ImageSearch;
import org.launchcode.git_artsy_backend.models.Tag;
import org.launchcode.git_artsy_backend.repositories.ImageSearchRepository;
import org.launchcode.git_artsy_backend.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Controller for managing image searches and tags.

@RestController
@RequestMapping("/api/images")
public class ImageSearchController {

    @Autowired
    private ImageSearchRepository imageRepository;

    @Autowired
    private TagRepository tagRepository;

    /**
     * Searches for images by a keyword.
     * @param keyword the keyword to search for.
     * @return a list of images matching the keyword.
     */
    @GetMapping("/search")
    public List<ImageSearch> searchImages(@RequestParam String keyword) {
        return imageRepository.findByTitleContainingOrDescriptionContaining(keyword, keyword);
    }

    /**
     * Adds a tag to an image.
     * @param imageId the ID of the image.
     * @param tagName the name of the tag.
     * @return the updated image.
     */
    @PostMapping("/{imageId}/tags")
    public ImageSearch addTagToImage(@PathVariable Long imageId, @RequestParam String tagName) {
        Optional<ImageSearch> imageOptional = imageRepository.findById(imageId);
        if (imageOptional.isPresent()) {
            ImageSearch image = imageOptional.get();
            Tag tag = tagRepository.findByName(tagName).orElseGet(() -> new Tag(tagName));
            image.getTags().add(tag);
            tag.getImages().add(image); // Assuming Tag has a getImages() method
            tagRepository.save(tag);
            return imageRepository.save(image);
        } else {
            throw new RuntimeException("Image not found");
        }
    }
}