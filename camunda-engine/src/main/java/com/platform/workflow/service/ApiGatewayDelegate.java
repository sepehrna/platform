package com.platform.workflow.service;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
public class ApiGatewayDelegate implements JavaDelegate {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${microservices.api-gateway.url}")
    private String apiGatewayUrl;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        // Create URI builder
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiGatewayUrl);

        // Get process variables
        String method = (String) execution.getVariable("method");
        Map<String, Object> parameters = (Map<String, Object>) execution.getVariable("parameters");
        Map<String, String> headersMap = (Map<String, String>) execution.getVariable("headers");
        String body = ((String) execution.getVariable("body"));
        String serviceName = (String) execution.getVariable("path");
        String servicePath = "/".concat(serviceName);
        uriBuilder.path(servicePath);

        // Add parameters
        if (parameters != null) {
            parameters.forEach((key, obj) -> {
                if (obj instanceof Date) {
                    // Format the Date to a string in yyyy-MM-dd format
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    obj = sdf.format(obj);
                }
                uriBuilder.queryParam(key, obj);
            });
        }

        // Create HTTP headers
        HttpHeaders headers = new HttpHeaders();
        if (headersMap != null) {
            headersMap.forEach(headers::add);
        }
        if (headers.isEmpty()) {
            headers.setContentType(MediaType.APPLICATION_JSON);
        }

        String url = uriBuilder.toUriString();

        // Create the request entity
        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

        // Call the REST service
        ResponseEntity<String> response = switch (method) {
            case "POST" -> restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            case "PUT" -> restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
            case "DELETE" -> restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);
            case "GET" -> restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
            default -> throw new IllegalArgumentException("Invalid HTTP method: " + method);
        };
        // Store the response status code and body as process variables
        execution.setVariable("responseStatusCode", response.getStatusCodeValue());
        execution.setVariable(execution.getCurrentActivityId().concat("_").concat("outputs"), response.getBody());
    }
}
