package org.launchcode.git_artsy_backend.controllers;

import org.launchcode.git_artsy_backend.models.Profile;
import org.launchcode.git_artsy_backend.models.User;
import org.launchcode.git_artsy_backend.repositories.ProfileRepo;
import org.launchcode.git_artsy_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class ArtistSearchController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepo profileRepo;

    @GetMapping("/search")
    public List<User> searchArtist(@RequestParam String searchQuery) {
        Set<Long> seenUserIds = new HashSet<>(); // Use a Set to track unique user IDs
        List<User> users = new ArrayList<>();

        Optional<List<User>> usersByUserName = userRepository.findByUsernameContainingIgnoreCase(searchQuery);
        if (usersByUserName.isPresent()) {
            for (User user : usersByUserName.get()) {
                // checks if user role is artist and makes sure that the user ID is not a duplicate
                if (user.getRole().equals("ARTIST") && seenUserIds.add(user.getUser_id())) {
                    users.add(user);
                }
            }
        }

        Optional<List<Profile>> profiles = profileRepo.findByNameContainingIgnoreCase(searchQuery);
        if (profiles.isPresent()) {
            for (Profile profile : profiles.get()) {
                // gets profile if it exists
                User user = userRepository.findById(profile.getUser().getUser_id()).orElse(null);
                // checks if user role is artist and makes sure that the user ID is not a duplicate
                if (user != null && user.getRole().equals("ARTIST") && seenUserIds.add(user.getUser_id())) {
                    users.add(user);
                }
            }
        }

        return users;
    }
}
