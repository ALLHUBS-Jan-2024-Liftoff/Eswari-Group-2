package org.launchcode.git_artsy_backend.controllers;

import org.launchcode.git_artsy_backend.models.Notification;
import org.launchcode.git_artsy_backend.models.User;
import org.launchcode.git_artsy_backend.repositories.NotificationRepo;
import org.launchcode.git_artsy_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("gitartsy/api/notifications")
@CrossOrigin(origins = "http://localhost:5173")
public class NotificationController {
    @Autowired
    private NotificationRepo notificationRepo;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/saw")
    public ResponseEntity<String> markNotificationAsRead(
            @RequestParam Long userId,
            @RequestParam Integer artworkId) {

        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(404).body("User not found");
        }

//        Optional<Notification> notificationOpt = notificationRepo.findByUserIdAndArtworkId(userId, artworkId);
//        if (notificationOpt.isEmpty()) {
//            return ResponseEntity.status(404).body("Notification not found");
//        }
//
//        Notification notification = notificationOpt.get();
//        notification.setRead(true);
////        notificationService.save(notification);

        return ResponseEntity.ok("Notification marked as read");
    }
}
