package data;

import org.launchcode.git_artsy_backend.ProductTag;
import org.springframework.data.repository.CrudRepository;

public interface ProductTagRepository extends CrudRepository<ProductTag, Long> {
}
