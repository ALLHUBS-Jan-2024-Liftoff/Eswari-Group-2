package org.launchcode.git_artsy_backend.repositories;

import org.launchcode.git_artsy_backend.models.Profile;
import org.launchcode.git_artsy_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface ProfileRepo extends JpaRepository<Profile, Integer> {
    Optional<Profile> findByUser(User user);
}
