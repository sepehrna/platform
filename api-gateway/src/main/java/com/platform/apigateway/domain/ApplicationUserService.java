package com.platform.apigateway.domain;

import com.platform.apigateway.domain.entities.ApplicationUsers;

public interface ApplicationUserService {

    ApplicationUsers getApplicationUsers();

    void initiateUsers() throws Exception;

}
