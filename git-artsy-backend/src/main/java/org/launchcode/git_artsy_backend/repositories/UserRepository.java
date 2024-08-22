package org.launchcode.git_artsy_backend.repositories;

import org.launchcode.git_artsy_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    //finds username and ignores case
    Optional<List<User>> findByUsernameContainingIgnoreCase(String username);
}