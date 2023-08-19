//package com.platform.apigateway.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//@Configuration
//@EnableWebFluxSecurity
//@DependsOn("initiateUsers")
//public class SecurityConfiguration {
//    @Bean
//    public SecurityWebFilterChain setSecurityWebFilterChains(ServerHttpSecurity http) {
//        return http
//                .csrf(ServerHttpSecurity.CsrfSpec::disable)
//                .authorizeExchange(auth -> auth
//                        .pathMatchers("*/**").hasRole("ADMIN")
//                        .pathMatchers("/swagger-ui.html").permitAll()
//                        .anyExchange().authenticated()
//                )
//                .build();
//    }
//}
