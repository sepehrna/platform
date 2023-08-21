package com.platform.apigateway.service;

import com.platform.apigateway.domain.ApplicationMicroservicesServices;
import com.platform.apigateway.domain.OpenApiDocService;
import com.platform.apigateway.domain.entities.Microservices;
import com.platform.apigateway.domain.entities.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;


@Service
@DependsOn({"initiateApplicationMicroservices"})
public class OpenOpenApiDocServiceImpl implements OpenApiDocService {

    private final ApplicationMicroservicesServices applicationMicroservicesServices;
    private final Map<String, OpenAPI> openAPIS = new HashMap<>();
    private final Logger logger = LoggerFactory.getLogger(OpenOpenApiDocServiceImpl.class);

    @Autowired
    public OpenOpenApiDocServiceImpl(ApplicationMicroservicesServices applicationMicroservicesServices) {
        this.applicationMicroservicesServices = applicationMicroservicesServices;
    }

    @Override
    @Bean
    public void initializeOpenApiDocuments() {
        Microservices microservices = applicationMicroservicesServices.getAllApplicationMicroservices();
        for (Server microservice : microservices.getServers()) {
            String uriString = microservice.getProtocol()
                    + "://"
                    + microservice.getAddress()
                    + ":" + microservice.getPort()
                    + microservice.getDescriptorPath();
            URI uri = URI.create(uriString);
            try {
                OpenAPI parsedOpenApiDocument = parseOpenApiDocument(uri.toString());
                openAPIS.putIfAbsent(microservice.getName(), parsedOpenApiDocument);
            } catch (Exception e) {
                if (microservice.getIsMandatory()) {
                    String logMessage = "--------------Microservice is not available: " + microservice.getName() + "--------------";
                    System.out.println(logMessage);
                    throw new RuntimeException(e);
                }
            }
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
