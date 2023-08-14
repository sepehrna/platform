package com.platform.apigateway.service;

import com.platform.apigateway.domain.ApplicationMicroservicesServices;
import com.platform.apigateway.domain.entities.Microservices;
import com.platform.apigateway.service.dto.MicroservicesProperties;
import com.platform.apigateway.service.mapper.MicroservicesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class ApplicationMicroservicesServicesImpl implements ApplicationMicroservicesServices {

    private final MicroservicesMapper microservicesMapper;
    private final MicroservicesProperties microservicesProperties;
    private Microservices microservices;

    @Autowired
    public ApplicationMicroservicesServicesImpl(MicroservicesMapper microservicesMapper, MicroservicesProperties microservicesProperties) {
        this.microservicesMapper = microservicesMapper;
        this.microservicesProperties = microservicesProperties;
    }

    @Override
    @Bean
    public void initiateApplicationMicroservices() {
        this.microservices = microservicesMapper.map(microservicesProperties);
    }

    @Override
    public Microservices getAllApplicationMicroservices() {
        return microservices;
    }

}
