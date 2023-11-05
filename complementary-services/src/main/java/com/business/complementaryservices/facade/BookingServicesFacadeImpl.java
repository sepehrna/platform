package com.business.complementaryservices.facade;

import com.business.complementaryservices.dto.BookingDto;
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

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingServicesFacadeImpl implements BookingServicesFacade {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${microservices.api-gateway.url}")
    private String apiGatewayUrl;

    public List<BookingDto> getBookingsByCheckInDate(LocalDate checkInDate) throws JsonProcessingException {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiGatewayUrl);

        String serviceName = "bookings/date/" + checkInDate;
        String servicePath = "/".concat(serviceName);
        uriBuilder.path(servicePath);

        HttpHeaders headers = new HttpHeaders();
        String url = uriBuilder.toUriString();
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

        return new ObjectMapper().readValue(response.getBody(), new TypeReference<List<BookingDto>>() {
        });
    }

    @Override
    public BookingDto bookRoomType(BookingDto bookingDto) throws JsonProcessingException, JSONException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiGatewayUrl);

        String serviceName = "booking";
        String servicePath = "/".concat(serviceName);
        JSONObject requestBody = new JSONObject();
        requestBody.put("customerId", bookingDto.getCustomerId());
        requestBody.put("roomTypeId", bookingDto.getRoomTypeId());
        requestBody.put("checkInDate", bookingDto.getCheckInDate());
        requestBody.put("checkOutDate", bookingDto.getCheckOutDate());
        uriBuilder.path(servicePath);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        String url = uriBuilder.toUriString();
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody.toString(), headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        return new ObjectMapper().readValue(response.getBody(), BookingDto.class);
    }
}
