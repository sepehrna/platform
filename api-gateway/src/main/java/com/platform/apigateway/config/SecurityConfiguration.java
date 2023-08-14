//package com.platform.apigateway.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.SecurityFilterChain;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//@EnableWebSecurity
//@DependsOn("initiateUsers")
//public class SecurityConfiguration {
//
//    @Bean
//    public MapReactiveUserDetailsService userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//        return new MapReactiveUserDetailsService(user);
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.httpBasic(withDefaults());
//        return http.build();
//    }
//
//    /*@Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web -> web.ignoring().requestMatchers("/login-page"));
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//            return new InMemoryUserDetailsManager();
//    }*/
//
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        return http
////                .csrf(AbstractHttpConfigurer::disable)
////                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers(HttpMethod.GET, "/gateway-services/*")
////                        .hasRole("ADMIN")
////                        .requestMatchers("/swagger-ui/*")
////                        .permitAll()
////                        .requestMatchers("/swagger-ui.html")
////                        .permitAll()
////                        .anyRequest()
////                        .authenticated()
////                )
////                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
////                .rememberMe(Customizer.withDefaults())
////                .build();
////    }
//}
