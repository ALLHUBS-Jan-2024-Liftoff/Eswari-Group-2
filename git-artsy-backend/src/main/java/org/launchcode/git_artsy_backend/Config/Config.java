package org.launchcode.git_artsy_backend.Config;

import org.launchcode.git_artsy_backend.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
public class Config {

    private final UserRepository userRepository;

    public Config(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    /**
     * Defines a bean for CORS (Cross-Origin Resource Sharing) filter.
     * This configuration allows application to handle requests from specified origins.
   */

    @Bean
    public CorsFilter corsFilter() {
        // Create a source for URL-based CORS configuration.
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // Define a new CORS configuration.
        CorsConfiguration config = new CorsConfiguration();

        // Allow requests from the specified origin (React app's origin).
        config.addAllowedOrigin("http://localhost:5173");

        // Allow all HTTP methods (GET, POST, PUT, DELETE, etc.).
        config.addAllowedMethod("*");

        config.addAllowedHeader("*");
        config.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
