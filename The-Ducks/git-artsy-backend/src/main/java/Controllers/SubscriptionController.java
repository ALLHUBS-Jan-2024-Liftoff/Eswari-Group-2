package Controllers;

import org.launchcode.git_artsy_backend.Subscription;

import java.util.List;
import java.util.Optional;

public class SubscriptionController {

    private final SubscriptionRepository subscriptionRepository;

    // Constructor to inject the data.SubscriptionRepository dependency
    public SubscriptionController(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    // Get all subscriptions
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    // Get a subscription by its ID
    public Subscription getSubscriptionById(Long id) {
        Optional<Subscription> subscription = subscriptionRepository.findById(id);
        return subscription.orElse(null); // Return null if subscription not found
    }

    // Create a new subscription
    public Subscription createSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    // Delete a subscription by its ID
    public void deleteSubscription(Long id) {
        if (subscriptionRepository.existsById(id)) {
            subscriptionRepository.deleteById(id);
        }
    }
}
