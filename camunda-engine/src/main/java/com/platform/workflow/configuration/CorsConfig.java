package com.platform.workflow.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/engine-rest/**")
                .allowedOrigins("http://localhost:3000")  // Your frontend's URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("Content-Type", "Authorization", "Access-Control-Request-Method")
                .allowCredentials(true)  // This allows cookies and authentication
                .maxAge(3600);  // This defines how long the results of a preflight request can be cached
    }
}
