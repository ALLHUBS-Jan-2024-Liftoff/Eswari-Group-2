package org.launchcode.git_artsy_backend.repositories;


import org.launchcode.git_artsy_backend.models.Artworks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface ArtworksRepo extends JpaRepository<Artworks, Integer> {
    // Search by title or description containing keywords
    static List<Artworks> findByTitleContainingOrDescriptionContaining(String titleKeyword, String descriptionKeyword) {
        return null;
    }

    // Search artworks by profile ID
    List<Artworks> findByProfileId(Integer profileId);
}