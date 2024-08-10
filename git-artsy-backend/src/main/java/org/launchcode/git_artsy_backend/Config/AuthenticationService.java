package org.launchcode.git_artsy_backend.Config;

import org.launchcode.git_artsy_backend.models.User;
import org.launchcode.git_artsy_backend.models.dto.LoginDTO;
import org.launchcode.git_artsy_backend.models.dto.RegisterDTO;
import org.launchcode.git_artsy_backend.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterDTO registerDTO) {
        User newUser = new User();
            newUser.setUsername(registerDTO.getUsername());
            newUser.setEmail(registerDTO.getEmail());
            newUser.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
            newUser.setRole(registerDTO.getRole());
            newUser.setCreatedAt(LocalDateTime.now());
            newUser.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(newUser);
    }

    public User authenticate(LoginDTO loginDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(),
                        loginDTO.getPassword()
                )
        );

        return userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow();
    }
}
