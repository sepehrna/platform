package com.platform.apigateway.service;

import com.platform.apigateway.domain.ApplicationUserService;
import com.platform.apigateway.domain.entities.ApplicationUsers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserManagerServiceImpl implements ApplicationUserService {

    private final Logger logger = LoggerFactory.getLogger(UserManagerServiceImpl.class);
    @Override
    public ApplicationUsers getApplicationUsers() {
        return null;
    }

    @Override
    public void initiateUsers() throws RuntimeException {
        ApplicationUsers applicationUsers = getApplicationUsers();
        if (applicationUsers.getApplicationUserDetails() == null || applicationUsers.getApplicationUserDetails().isEmpty()) {
            throw new RuntimeException("The user manager service is not available");
        }
    }
}
