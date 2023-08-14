package com.platform.apigateway.domain;

import io.swagger.v3.oas.models.OpenAPI;

import java.util.List;
import java.util.Map;

public interface OpenApiDocService {

    void initializeOpenApiDocuments();

    Map<String, OpenAPI> getAllOpenApiDocs();

    OpenAPI parseOpenApiDocument(String openApiDocUrl);

}
