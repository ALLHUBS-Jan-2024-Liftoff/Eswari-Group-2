package org.launchcode.git_artsy_backend.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
public class Config {

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
