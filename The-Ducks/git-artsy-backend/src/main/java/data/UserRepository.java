package data;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<SecurityProperties.User, Long> {
}
