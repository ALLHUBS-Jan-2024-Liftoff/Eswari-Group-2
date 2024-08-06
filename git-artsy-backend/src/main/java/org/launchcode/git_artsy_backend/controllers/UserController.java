package org.launchcode.git_artsy_backend.controllers;

import org.launchcode.git_artsy_backend.models.User;
import org.launchcode.git_artsy_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String getAllUsers(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "user/list";
    }

    @PostMapping("/newUser")
    public User createUser(@RequestParam String username, @RequestParam String email, @RequestParam String password, @RequestParam String verifyPassword, @RequestParam String role) {

        if (password.equals(verifyPassword)) {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setPassword(password);
            newUser.setRole(role);
            newUser.setCreatedAt(LocalDateTime.now());
            newUser.setUpdatedAt(LocalDateTime.now());

            return userRepository.save(newUser);
        } else {
            throw new IllegalArgumentException("Passwords must match.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestParam String email, @RequestParam String password){
        User userLoggingIn = userRepository.findByEmail(email);

        if (userLoggingIn == null || !password.equals(userLoggingIn.getPassword())) {
            throw new IllegalArgumentException("Cannot find user");
        }
        return ResponseEntity.ok(userLoggingIn);
    }

//    @PostMapping("delete/{id}")
//    public String deleteUser(@PathVariable("id") Long id){
//        userRepository.deleteById(id);
//        return "redirect:/users";
//    }
}