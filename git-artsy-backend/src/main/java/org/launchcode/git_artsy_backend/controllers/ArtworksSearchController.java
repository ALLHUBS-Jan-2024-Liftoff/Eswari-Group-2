package org.launchcode.git_artsy_backend.controllers;

import org.launchcode.git_artsy_backend.models.Artworks;
import org.launchcode.git_artsy_backend.models.dto.ArtworkSearchDto;
import org.launchcode.git_artsy_backend.repositories.ArtworksRepo;
import org.launchcode.git_artsy_backend.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gitartsy/api/search")
public class ArtworksSearchController {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ArtworksRepo artworksRepo;

    @GetMapping
    public List<ArtworkSearchDto> searchArtworks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) List<Long> tags) {

        Set<Artworks> combinedResults = new HashSet<>();

        // Search by title or description
        if (title != null && !title.isEmpty()) {
            List<Artworks> artworksByTitleOrDescription = artworksRepo.findByTitleContainingOrDescriptionContaining(title, title);
            combinedResults.addAll(artworksByTitleOrDescription);
        }

        // Search by tags
        if (tags != null && !tags.isEmpty()) {
            List<Artworks> artworksByTags = tagRepository.findAllById(tags)
                    .stream()
                    .flatMap(tag -> tag.getArtworks().stream())
                    .collect(Collectors.toList());
            combinedResults.addAll(artworksByTags);
        }

        // Convert to DTOs
        return combinedResults.stream()
                .map(artwork -> new ArtworkSearchDto(
                        artwork.getFileDownloadUri(),
                        artwork.getTitle(),
                        artwork.getDescription(),
                        artwork.getTags().stream().map(tag -> tag.getName()).collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }
}