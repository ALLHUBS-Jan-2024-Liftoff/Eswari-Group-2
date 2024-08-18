
package org.launchcode.git_artsy_backend.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;


@Configuration
public class Config {

    // Create spring-managed object to allow the app to access our filter
    @Bean
    public AuthFilter authenticationFilter() {
        return new AuthFilter();
    }

    // Register the filter with the Spring container
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( authenticationFilter() );
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
