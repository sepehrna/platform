package com.platform.apigateway.service;

import com.platform.apigateway.domain.ApplicationMicroservicesServices;
import com.platform.apigateway.domain.OpenApiDocService;
import com.platform.apigateway.domain.entities.Microservices;
import com.platform.apigateway.domain.entities.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;


@Service
@DependsOn({"initiateApplicationMicroservices"})
public class OpenOpenApiDocServiceImpl implements OpenApiDocService {

    private final ApplicationMicroservicesServices applicationMicroservicesServices;
    private final Map<String, OpenAPI> openAPIS = new HashMap<>();

    @Autowired
    public OpenOpenApiDocServiceImpl(ApplicationMicroservicesServices applicationMicroservicesServices) {
        this.applicationMicroservicesServices = applicationMicroservicesServices;
    }

    @Override
    @Bean
    public void initializeOpenApiDocuments() {
        Microservices microservices = applicationMicroservicesServices.getAllApplicationMicroservices();
        for (Server microservice : microservices.getServers()) {
            URI openApiDocUrl = new DefaultUriBuilderFactory()
                    .builder()
                    .scheme(microservice.getProtocol())
                    .host(microservice.getAddress())
                    .port(microservice.getPort())
                    .path(microservice.getDescriptorPath())
                    .build();
            openAPIS.putIfAbsent(microservice.getName(), parseOpenApiDocument(openApiDocUrl.toString()));
        }
    }

    @Override
    public Map<String, OpenAPI> getAllOpenApiDocs() {
        return openAPIS;
    }

    @Override
    public OpenAPI parseOpenApiDocument(String openApiDocUrl) {
        return new OpenAPIV3Parser().read(openApiDocUrl);
    }

}
