package com.business.complementaryservices.domain;

import com.business.complementaryservices.dto.BookingDto;
import com.business.complementaryservices.dto.RoomTypeDto;
import com.business.complementaryservices.dto.RoomTypeSelectorDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.configurationprocessor.json.JSONException;

import java.time.LocalDate;
import java.util.List;

public interface BusinessComplementaryService {

    List<RoomTypeSelectorDto> getAvailableRoomTypes(LocalDate checkInDate) throws JsonProcessingException;

    String defineRoomTypeCustomized(RoomTypeDto roomTypeDto) throws JsonProcessingException, JSONException;

    String bookRoomTypeCustomized(BookingDto bookingDto) throws JsonProcessingException, JSONException;

}
