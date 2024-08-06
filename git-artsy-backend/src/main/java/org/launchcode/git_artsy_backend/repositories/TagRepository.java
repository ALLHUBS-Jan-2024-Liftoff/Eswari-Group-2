package org.launchcode.git_artsy_backend.repositories;
import org.launchcode.git_artsy_backend.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


// Repository interface for Tag entities.
@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

     Optional<Tag> findByName(String tagName);
 }
