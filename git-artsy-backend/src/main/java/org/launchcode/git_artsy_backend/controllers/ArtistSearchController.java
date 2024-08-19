package org.launchcode.git_artsy_backend.controllers;

import org.launchcode.git_artsy_backend.models.Profile;
import org.launchcode.git_artsy_backend.models.User;
import org.launchcode.git_artsy_backend.repositories.ProfileRepo;
import org.launchcode.git_artsy_backend.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class ArtistSearchController {

    private UserRepository userRepository;
    private ProfileRepo profileRepo;

    @GetMapping("/search")
    public List<User> searchArtist(@RequestParam String searchQuery) {
        List<User> users = new ArrayList<User>();

        Optional<List<User>> usersByUserName = userRepository.findByUsernameContainingIgnoreCase(searchQuery);
        if (usersByUserName.isPresent()) {
            for (User user : usersByUserName.get()){
                if (user.getRole().equals("ARTIST")){
                    users.add(user);
                }
            }
        }

        Optional<List<Profile>> profiles = profileRepo.findByNameContainingIgnoreCase(searchQuery);
        if (profiles.isPresent()) {
            for (Profile profile : profiles.get()){
                User user = userRepository.findByUserId(profile.getUser().getUser_id());
                if (user.getRole().equals("ARTIST")){
                    users.add(user);
                }
            }
        }

        return users;
    }
}
