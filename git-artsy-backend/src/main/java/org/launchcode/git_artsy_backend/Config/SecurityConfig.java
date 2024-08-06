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
                                "/gitartsy/api/artworks/**", "/uploads/**").permitAll()
                        .anyRequest().authenticated()  // Require authentication for any other requests
                );
        return http.build();
    }

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + uploadPath + "/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:5173");
    }

}
