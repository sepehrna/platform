package com.platform.apigateway.config;

import com.platform.apigateway.domain.ApplicationUserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationUsersConfig {

    private final ApplicationUserService userManagerApplicationUserService;
    private final ApplicationUserService jaxbApplicationUserService;

    @Getter
    private ApplicationUserService applicationUserService;

    @Autowired
    public ApplicationUsersConfig(@Qualifier("userManagerServiceImpl") ApplicationUserService userManagerApplicationUserService
            , @Qualifier("jaxbApplicationUserServiceImpl") ApplicationUserService jaxbApplicationUserService) {
        this.userManagerApplicationUserService = userManagerApplicationUserService;
        this.jaxbApplicationUserService = jaxbApplicationUserService;
    }

    @Bean
    void initiateUsers() throws RuntimeException {
        try {
            userManagerApplicationUserService.initiateUsers();
            applicationUserService = userManagerApplicationUserService;
        } catch (Exception e) {
            try {
                jaxbApplicationUserService.initiateUsers();
                applicationUserService = jaxbApplicationUserService;
            } catch (Exception ex) {
                throw new RuntimeException("Users not found!!!");
            }
        }
    }

}
