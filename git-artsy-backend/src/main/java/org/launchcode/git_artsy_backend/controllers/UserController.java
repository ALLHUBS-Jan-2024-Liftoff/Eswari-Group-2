package org.launchcode.git_artsy_backend.controllers;

import org.launchcode.git_artsy_backend.Config.AuthenticationService;
import org.launchcode.git_artsy_backend.Config.JwtService;
import org.launchcode.git_artsy_backend.models.User;
import org.launchcode.git_artsy_backend.models.dto.LoginDTO;
import org.launchcode.git_artsy_backend.models.dto.RegisterDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
//@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public UserController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterDTO registerDTO) {
        User registeredUser = authenticationService.signup(registerDTO);

        return ResponseEntity.ok(registeredUser);
    }

//    @PostMapping("/login")
//    public ResponseEntity<Object> authenticate(@RequestBody LoginDTO loginDTO) {
//        User authenticatedUser = authenticationService.authenticate(loginDTO);
//
//        String jwtToken = jwtService.generateToken(authenticatedUser);
//
//        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());
//
//        return ResponseEntity.ok(loginResponse);
//    }


//    @GetMapping("/users")
//    public ResponseEntity<List<User>> getAllUsers() {
//        List<User> users = userRepository.findAll();
//        return ResponseEntity.ok(users);
//    }

//    @PostMapping("/newUser")
//    public User createUser(@RequestBody RegisterDTO registerDTO) {
//
//        if (registerDTO.getPassword().equals(registerDTO.getVerifyPassword())) {
//            User newUser = new User();
//            String hashedPw = passwordEncoder.encode(password);
//            newUser.setUsername(registerDTO.getUsername());
//            newUser.setEmail(registerDTO.getEmail());
//            newUser.setPassword(registerDTO.getPassword());
//            newUser.setRole(registerDTO.getRole());
//            newUser.setCreatedAt(LocalDateTime.now());
//            newUser.setUpdatedAt(LocalDateTime.now());
//
//            return userRepository.save(newUser);
//        } else {
//            throw new IllegalArgumentException("Passwords must match.");
//        }
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//        Optional<User> currentUser = userRepository.findById(id);
//        if (currentUser.isPresent()) {
//            try {
//                userRepository.deleteById(id);
//                return ResponseEntity.ok().build();
//            } catch (Exception e) {
//                e.printStackTrace();
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//            }
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}