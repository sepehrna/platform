package com.business.complementaryservices.facade;

import com.business.complementaryservices.dto.RoomTypeDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class RoomServicesFacadeImpl implements RoomServicesFacade {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${microservices.api-gateway.url}")
    private String apiGatewayUrl;

    @Override
    public List<RoomTypeDto> getAllRoomTypes() throws JsonProcessingException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiGatewayUrl);

        String serviceName = "room_types";
        String servicePath = "/".concat(serviceName);
        uriBuilder.path(servicePath);

        HttpHeaders headers = new HttpHeaders();
        String url = uriBuilder.toUriString();
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

        return new ObjectMapper().readValue(response.getBody(), new TypeReference<List<RoomTypeDto>>() {
        });
    }

    @Override
    public RoomTypeDto defineRoomType(RoomTypeDto roomTypeDto) throws JsonProcessingException, JSONException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiGatewayUrl);

        String serviceName = "room_types";
        String servicePath = "/".concat(serviceName);
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", roomTypeDto.getName());
        requestBody.put("description", roomTypeDto.getDescription());
        requestBody.put("price", roomTypeDto.getPrice());
        uriBuilder.path(servicePath);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        String url = uriBuilder.toUriString();
        String jsonBody = requestBody.toString();
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        return new ObjectMapper().readValue(response.getBody(), RoomTypeDto.class);
    }
}
