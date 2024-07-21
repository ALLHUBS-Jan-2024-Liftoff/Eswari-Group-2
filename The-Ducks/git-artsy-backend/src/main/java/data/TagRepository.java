package data;

import org.launchcode.git_artsy_backend.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long> {
}
