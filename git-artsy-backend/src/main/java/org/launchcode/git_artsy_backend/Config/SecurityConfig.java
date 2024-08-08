package org.launchcode.git_artsy_backend.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;


import java.util.List;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfig implements WebMvcConfigurer {

    // Defines a bean for the security filter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(request -> {
                    var corsConfig = new org.springframework.web.cors.CorsConfiguration();
                    corsConfig.setAllowedOrigins(List.of("http://localhost:5173"));
                    corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
                    corsConfig.setAllowedHeaders(List.of("*"));
                    corsConfig.setAllowCredentials(true);
                    corsConfig.setMaxAge(3600L);
                    return corsConfig;
                }))
                .csrf(AbstractHttpConfigurer::disable)  // Disabling CSRF protection
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                // Allows these endpoints without authentication
                                "/gitartsy/api/artworks/**", "/uploads/**","/gitartsy/api/tags/**","/api/user/**").permitAll()
                        .anyRequest().authenticated()  // Require authentication for any other requests
                );
        return http.build();
    }

    // Injects the value of the upload path from the application properties
    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Maps URLs starting with /uploads/ to files in the specified location
        registry.addResourceHandler("/uploads/**")

                .addResourceLocations("file:" + uploadPath + "/");// Specifies the file location for the resources

    }
}
