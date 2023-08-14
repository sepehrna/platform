//package com.platform.apigateway.config;
//
//import com.platform.apigateway.domain.OpenApiDocService;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.PathItem;
//import io.swagger.v3.parser.OpenAPIV3Parser;
//import org.springdoc.core.models.GroupedOpenApi;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.gateway.route.RouteDefinition;
//import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.context.annotation.Lazy;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//@Configuration
//@EnableDiscoveryClient
//public class DocumentationConfig {
//
//    private final OpenApiDocService openApiDocService;
//
//    @Autowired
//    public DocumentationConfig(OpenApiDocService openApiDocService) {
//        this.openApiDocService = openApiDocService;
//    }
//
//    @Bean
//    public OpenAPI apis() {
////        List<GroupedOpenApi> result = new ArrayList<>();
////        for (Map.Entry<String, OpenAPI> entry : openApiDocService.getAllOpenApiDocs().entrySet()) {
////            GroupedOpenApi.Builder builder = GroupedOpenApi.builder()
////                    .group(entry.getKey())
////                    .displayName(entry.getKey());
////            for (Map.Entry<String, PathItem> pathEntry : entry.getValue().getPaths().entrySet()) {
////                builder.pathsToMatch(pathEntry.getValue().get$ref());
////            }
////            result.add(builder.build());
////        }
//
//        return openApiDocService.getAllOpenApiDocs().values().stream().toList().get(0);
//    }
//
//    //    @Bean
////    @Lazy(false)
////    public List<GroupedOpenApi> apis(RouteLocator locator) {
////        List<GroupedOpenApi> groups = new ArrayList<>();
////        List<Route> routes = locator.getRoutes().collectList().block();
////        if (routes != null) {
////            for (Route route : routes) {
////                System.out.println("id: " + route.getId() + "  " + route.getUri().toString());
////            }
////            routes.forEach(route -> {
////                String name = route.getId().replaceAll("-service", "");
////                groups.add(GroupedOpenApi
////                        .builder()
////                        .pathsToMatch("/**")
////                        .group(name)
////                        .build());
////            });
////        }
////        return groups;
////    }
////    @Bean
////    @Lazy(false)
////    public List<GroupedOpenApi> apis(RouteDefinitionLocator locator) {
////        List<GroupedOpenApi> groups = new ArrayList<>();
////        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
////        for (RouteDefinition definition : definitions) {
////            System.out.println("id: " + definition.getId() + "  " + definition.getUri().toString());
////        }
////        definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-service")).forEach(routeDefinition -> {
////            String name = routeDefinition.getId().replaceAll("-service", "");
////            GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build();
////        });
////        return groups;
////    }
//}
