package com.platform.apigateway.domain;

import com.platform.apigateway.domain.entities.ApplicationUserDetail;
import com.platform.apigateway.domain.entities.ApplicationUsers;

public interface ApplicationUserService {

    ApplicationUsers getApplicationUsers();

    void initiateUsers() throws Exception;

    default ApplicationUserDetail verifyUser(String token) {
        ApplicationUserDetail applicationUserDetail = new ApplicationUserDetail();
        applicationUserDetail.setAccessToken(token);
        applicationUserDetail.setUsername("peter");
        applicationUserDetail.setPassword("123");
        applicationUserDetail.setRole("ADMIN");
        return applicationUserDetail;
    }
}
