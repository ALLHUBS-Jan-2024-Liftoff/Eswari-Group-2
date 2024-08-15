package org.launchcode.git_artsy_backend.controllers;

import org.launchcode.git_artsy_backend.models.Artworks;
import org.launchcode.git_artsy_backend.models.Tag;
import org.launchcode.git_artsy_backend.repositories.ArtworksRepo;
import org.launchcode.git_artsy_backend.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Controller for managing artworks searches and tags.

@RestController
@RequestMapping("/api/artworks")
public class ImageSearchController {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ArtworksRepo artworksRepo;
    private Integer artwork_Id;

    /**
     * Searches for artworkss by a keyword.
     * @param keyword the keyword to search for.
     * @return a list of artworkss matching the keyword.
     */
    @GetMapping("/search")
    public List<Artworks> searchartworkss(@RequestParam String keyword) {
        return ArtworksRepo.findByTitleContainingOrDescriptionContaining(keyword, keyword);
    }

    /**
     * Adds a tag to an artworks.
     * @param artworkId the ID of the artworks.
     * @param tagName the name of the tag.
     * @return the updated artworks.
     */
    @PostMapping("/{artworkId}/tags")
    protected Artworks addTagToArtworks(@PathVariable Integer artworkId, @RequestParam String tagName) {
        // Corrected variable name from ArtworkId to artworkId to match standard Java naming conventions
        Optional<Artworks> artworksOptional = artworksRepo.findById(artworkId);
        if (artworksOptional.isPresent()) {
            Artworks artwork = artworksOptional.get(); // Renamed variable for clarity and correct Java conventions
            // Fetch or create the tag
            Tag tag = tagRepository.findByName(tagName).orElseGet(() -> new Tag(tagName));
            artwork.getTags().add(tag);
            tag.getArtworks().add(artwork);
            tagRepository.save(tag); // Save the tag to update the relationship
            return artworksRepo.save(artwork); // Save the artwork with the new tag
        } else {
            throw new RuntimeException("Artwork not found");
        }
    }
}