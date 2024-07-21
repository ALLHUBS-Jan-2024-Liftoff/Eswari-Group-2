package org.launchcode.git_artsy_backend.Repo;


import org.launchcode.git_artsy_backend.Models.Artworks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface ArtworksRepo  extends CrudRepository<Artworks, Integer> {

}
