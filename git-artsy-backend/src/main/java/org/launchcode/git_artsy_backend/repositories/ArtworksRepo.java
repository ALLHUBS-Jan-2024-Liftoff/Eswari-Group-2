package org.launchcode.git_artsy_backend.repositories;


import org.launchcode.git_artsy_backend.models.Artworks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface ArtworksRepo  extends JpaRepository<Artworks, Integer> {

}
