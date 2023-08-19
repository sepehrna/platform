package com.platform.workflow.configuration;

import com.platform.workflow.service.ApiGatewayDelegate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DelegatesConfiguration {

    @Bean
    public ApiGatewayDelegate getApiGatewayDelegate(){
        return new ApiGatewayDelegate();
    }

}
