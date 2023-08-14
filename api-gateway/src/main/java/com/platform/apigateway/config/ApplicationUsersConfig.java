package com.platform.apigateway.config;

import com.platform.apigateway.domain.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationUsersConfig {

    private final ApplicationUserService applicationUserService;

    @Autowired
    public ApplicationUsersConfig(ApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }

    @Bean
    void initiateUsers() throws Exception {
        applicationUserService.initiateUsers();
    }

}
