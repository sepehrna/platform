package com.platform.workflow.service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
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
        String pathVariable = ((String) execution.getVariable("path_variable"));
        String postVariablePath = ((String) execution.getVariable("post_variable_path"));
        Map<String, Object> bodyMap = (Map<String, Object>) execution.getVariable("body");

        String bodyJson = deserializeInputBody(bodyMap);

        String serviceName = (String) execution.getVariable("path");
        String servicePath = "/".concat(serviceName);
        servicePath = getServicePath(pathVariable, servicePath, postVariablePath);
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
        HttpEntity<String> requestEntity = new HttpEntity<>(bodyJson, headers);

        // Call the REST service
        ResponseEntity<String> response = switch (method) {
            case "POST" -> restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            case "PUT" -> restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
            case "DELETE" -> restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);
            case "GET" -> restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
            default -> throw new IllegalArgumentException("Invalid HTTP method: " + method);
        };
        // Store the response status code and bodyJson as process variables
        execution.setVariable("responseStatusCode", response.getStatusCodeValue());
        execution.setVariable(execution.getCurrentActivityId().concat("_").concat("outputs")
                , response.getBody());
    }

    private String getServicePath(String pathVariable, String servicePath, String postVariablePath) {
        if (pathVariable != null && !pathVariable.isEmpty()) {
            servicePath = servicePath.concat("/".concat(pathVariable));
            if (postVariablePath != null && !postVariablePath.isEmpty()) {
                servicePath = servicePath.concat("/".concat(postVariablePath));
            }
        }
        return servicePath;
    }

    private String deserializeInputBody(Map<String, Object> bodyMap) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Map.class, new JsonSerializer<>() {
            @Override
            public void serialize(Map map, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeStartObject();

                for (Object key : map.keySet()) {
                    Object value = map.get(key);
                    if (key instanceof String castedKey) {
                        if (castedKey.contains("_Integer")) {
                            castedKey = castedKey.substring(0, castedKey.indexOf("_Integer"));
                            int parsedInt = Integer.parseInt(value.toString());
                            gen.writeNumberField(castedKey, parsedInt);
                        } else if (value instanceof String) {
                            gen.writeStringField(key.toString(), value.toString().toUpperCase());
                        } else if (value instanceof Date) {
                            // Format the Date to a string in yyyy-MM-dd format
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            value = sdf.format(value);
                            gen.writeStringField(key.toString(), value.toString());
                        } else {
                            gen.writeObjectField(key.toString(), value);
                        }
                    }
                }
                gen.writeEndObject();
            }
        });

        objectMapper.registerModule(module);

        return objectMapper.writeValueAsString(bodyMap);
    }
}
