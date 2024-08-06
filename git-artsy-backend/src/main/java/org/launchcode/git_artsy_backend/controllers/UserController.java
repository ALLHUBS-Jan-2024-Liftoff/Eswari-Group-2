package org.launchcode.git_artsy_backend.Controllers;

import org.launchcode.git_artsy_backend.models.User;
import org.launchcode.git_artsy_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String getAllUsers(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "user/list";
    }

    @PostMapping("/newUser")
    public User createUser(@RequestParam String username, @RequestParam String email, @RequestParam String password, @RequestParam String verifyPassword, @RequestParam User.Role role) {

        if (password.equals(verifyPassword)) {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setPassword(password);
            newUser.setRole(role);

            return userRepository.save(newUser);
        } else {
            throw new IllegalArgumentException("Passwords must match.");
        }
    }

    @PostMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userRepository.deleteById(id);
        return "redirect:/users";
    }


}
