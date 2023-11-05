package com.business.complementaryservices.facade;

import com.business.complementaryservices.dto.RoomTypeDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.configurationprocessor.json.JSONException;

import java.util.List;

public interface RoomServicesFacade {

    List<RoomTypeDto> getAllRoomTypes() throws JsonProcessingException;

    RoomTypeDto defineRoomType(RoomTypeDto roomTypeDto) throws JsonProcessingException, JSONException;

}
