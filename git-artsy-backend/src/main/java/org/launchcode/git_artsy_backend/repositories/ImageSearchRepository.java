package org.launchcode.git_artsy_backend.repositories;

import org.launchcode.git_artsy_backend.models.ImageSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ImageSearchRepository extends JpaRepository<ImageSearch, Integer> {
    List<ImageSearch> findByTitleContainingOrDescriptionContaining(String title, String description);
}