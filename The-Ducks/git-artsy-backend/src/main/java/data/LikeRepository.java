package data;

import org.launchcode.git_artsy_backend.Like;
import org.springframework.data.repository.CrudRepository;

public interface LikeRepository extends CrudRepository<Like, Long> {
}

