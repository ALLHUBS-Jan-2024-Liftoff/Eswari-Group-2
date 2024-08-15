package org.launchcode.git_artsy_backend.repositories;


import org.launchcode.git_artsy_backend.models.Artworks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface ArtworksRepo  extends JpaRepository<Artworks, Integer> {

    static List<Artworks> findByTitleContainingOrDescriptionContaining(String keyword, String keyword1) {
        List<Artworks> artworks = null;
        return artworks;
    }

    Optional<Artworks> findById(Integer artworks_Id);
}
