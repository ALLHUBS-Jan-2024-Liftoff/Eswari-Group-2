package org.launchcode.git_artsy_backend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.launchcode.git_artsy_backend.models.User;
import org.launchcode.git_artsy_backend.models.dto.LoginDTO;
import org.launchcode.git_artsy_backend.models.dto.RegisterDTO;
import org.launchcode.git_artsy_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserRepository userRepository;

//    creates user session and matches user id with session key
    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getUser_id());
    }

    private static final String userSessionKey = "user";

//    gets user and user session key for user in session
    public User getUserFromSession(HttpSession session) {
        Long userId = (Long) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }

//    creates new user and saves them to database
    @PostMapping("/newUser")
    public ResponseEntity<Map> processRegistrationForm(@RequestBody RegisterDTO registerDTO,
                                                       HttpServletRequest request)  {
        ResponseEntity response = null;
        Map<String, String> responseBody = new HashMap<>();
        try{
//            checks entered email to see if user already exists with that email
            User existingUser = userRepository.findByEmail(registerDTO.getEmail());
            if(existingUser != null) {
                responseBody.put("message", "User Already Exists.");
                response = ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(responseBody);
            } else if(registerDTO.getUsername().isEmpty()) {
                responseBody.put("message", "Username required.");
                response = ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(responseBody);
            } else if (registerDTO.getPassword().isEmpty()) {
                responseBody.put("message", "Password required");
                response = ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(responseBody);
            } else if (registerDTO.getRole().isEmpty()) {
                responseBody.put("message", "Please select an account type");
                response = ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(responseBody);
            } else if (registerDTO.getVerifyPassword().isEmpty() || !registerDTO.getVerifyPassword().equals(registerDTO.getPassword())) {
                responseBody.put("message", "Passwords must match");
                response = ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(responseBody);
            } else if (existingUser == null && !registerDTO.getUsername().isEmpty() && !registerDTO.getPassword().isEmpty()){
                responseBody.put("message", "Given user details are successfully registered");
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(responseBody);
                User newUser = new User(registerDTO.getUsername(), registerDTO.getEmail(), registerDTO.getPassword(), registerDTO.getRole());
                setUserInSession(request.getSession(), newUser);
                userRepository.save(newUser);

            }
        }catch (Exception ex){
            responseBody.put("message", "An exception occurred due to " + ex.getMessage());
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(responseBody);
        }
        return response;
    }

//    logs existing user in
    @PostMapping("/login")
    public ResponseEntity<Map> processLoginForm(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {

        ResponseEntity response = null;
        Map<String, String> responseBody = new HashMap<>();
        User theUser = userRepository.findByEmail(loginDTO.getEmail());
        String password = loginDTO.getPassword();
        if (theUser == null) {
            responseBody.put("message", "Username does not exist");
            response = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(responseBody);
        }else if (!theUser.isMatchingPassword(password)) {
            responseBody.put("message", "Password does not match");
            response = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(responseBody);
        } else {
            setUserInSession(request.getSession(), theUser);
            responseBody.put("message", "User successfully logged in.");
            responseBody.put("username", theUser.getUsername());
            responseBody.put("userRole", theUser.getRole());
            response = ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(responseBody);
        }
        return  response;
    }

//    logs user out
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/";
    }
}