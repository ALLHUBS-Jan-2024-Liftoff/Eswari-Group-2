package org.launchcode.git_artsy_backend.Repo;git
import org.launchcode.git_artsy_backend.Models.Tag;

/**
 * Repository interface for Tag entities.
 * Provides CRUD operations for Tag entities.
 */
public interface TagRepository extends JpaRepository<Tag, Long> {
    // You can define custom query methods here if needed
}
