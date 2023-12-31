package com.business.complementaryservices.controller;


import com.business.complementaryservices.domain.BusinessComplementaryService;
import com.business.complementaryservices.dto.BookingDto;
import com.business.complementaryservices.dto.RoomTypeDto;
import com.business.complementaryservices.dto.RoomTypeSelectorDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = {"*", "*/**"}, maxAge = 3600)
@RequestMapping(path = "/business-complementary", consumes = {MediaType.ALL_VALUE})
@RestController
@OpenAPIDefinition(info = @Info(title = "business-complementary-api", version = "v1"))
public class BusinessComplementaryController {

    private final BusinessComplementaryService businessComplementaryService;

    @Autowired
    public BusinessComplementaryController(BusinessComplementaryService businessComplementaryService) {
        this.businessComplementaryService = businessComplementaryService;
    }

    @GetMapping(path = "/available-room-types")
    public ResponseEntity<List<RoomTypeSelectorDto>> getAvailableRoomTypes(@RequestParam
                                                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                           @NotNull LocalDate checkInDate) throws JsonProcessingException {
        return ResponseEntity
                .ok(businessComplementaryService.getAvailableRoomTypes(checkInDate));
    }

    @PostMapping(path = "/define-room-type-customized")
    public ResponseEntity<String> bookRoomTypeCustomized(@RequestBody RoomTypeDto roomTypeDto) throws JsonProcessingException, JSONException {
        return ResponseEntity
                .ok(businessComplementaryService.defineRoomTypeCustomized(roomTypeDto));
    }

    @PostMapping(path = "/book-room-type-customized", consumes = {MediaType.ALL_VALUE})
    public ResponseEntity<String> bookRoomTypeCustomized(@RequestBody BookingDto bookingDto) throws JsonProcessingException, JSONException {
        return ResponseEntity
                .ok(businessComplementaryService.bookRoomTypeCustomized(bookingDto));
    }
}
