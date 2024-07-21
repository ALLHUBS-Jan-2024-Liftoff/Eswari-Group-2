package data;

import org.launchcode.git_artsy_backend.Subscription;
import org.springframework.data.repository.CrudRepository;

public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {
}
