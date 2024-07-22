package org.launchcode.git_artsy_backend.Repo;
import org.launchcode.git_artsy_backend.Models.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


 // Repository interface for Tag entities.


@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {

}
