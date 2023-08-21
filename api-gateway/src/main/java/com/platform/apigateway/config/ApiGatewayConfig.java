package com.platform.apigateway.config;

import com.platform.apigateway.domain.OpenApiDocService;
import com.platform.apigateway.domain.ApplicationMicroservicesServices;
import com.platform.apigateway.domain.entities.Server;
import com.platform.apigateway.domain.entities.Microservices;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.Map;

@Configuration
@DependsOn({"initiateApplicationMicroservices", "initializeOpenApiDocuments"})
public class ApiGatewayConfig {

    private final ApplicationMicroservicesServices microservicesServices;
    private final OpenApiDocService openApiDocService;

    @Autowired
    public ApiGatewayConfig(ApplicationMicroservicesServices microservicesServices, OpenApiDocService openApiDocService) {
        this.microservicesServices = microservicesServices;
        this.openApiDocService = openApiDocService;
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        Microservices microservices = microservicesServices.getAllApplicationMicroservices();
        RouteLocatorBuilder.Builder routeLocatorBuilder = builder.routes();
        for (Server microservice : microservices.getServers()) {

            UriBuilder microserviceUriBuilder = new DefaultUriBuilderFactory()
                    .builder()
                    .scheme(microservice.getProtocol())
                    .host(microservice.getAddress())
                    .port(microservice.getPort());

            OpenAPI openAPI = openApiDocService.getAllOpenApiDocs().get(microservice.getName());
            if (openAPI != null) {
                URI microserviceUri;
                String expandedPath = "";
                if (microservice.getOpenApiVersion() != null && microservice.getOpenApiVersion().equals("3")) {
                    microserviceUri = microserviceUriBuilder.build();
                } else {
                    String uri = openAPI.getServers().get(0).getUrl();
                    String uriFromSetting = microservice.getProtocol()
                            + "://"
                            + microservice.getAddress()
                            + ":" + microservice.getPort();
                    if (uri.equalsIgnoreCase(uriFromSetting)) {
                        microserviceUri = URI.create(uri);
                    } else {
                        expandedPath = uri.substring(uriFromSetting.length());
                        microserviceUri = microserviceUriBuilder.build();
                    }
                }
                for (Map.Entry<String, PathItem> entry : openAPI.getPaths().entrySet()) {
                    String path = expandedPath + entry.getKey();
                    PathItem pathItem = entry.getValue();
                    routeLocatorBuilder.route(r -> r.path(path)
                            .filters(f -> {
                                if (pathItem.getParameters() != null) {
                                    pathItem.getParameters().forEach(parameter -> f.addRequestParameter(parameter.getName(), parameter.getIn()));
                                }
                                return f;
                            })
                            .uri(microserviceUri));
                }
            }
        }
        return routeLocatorBuilder.build();
    }
}
