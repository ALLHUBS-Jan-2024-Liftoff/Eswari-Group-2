package org.launchcode.git_artsy_backend.Repo;

import org.launchcode.git_artsy_backend.Models.ArtistProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistProfileRepo extends JpaRepository<ArtistProfile, Integer> {
}
