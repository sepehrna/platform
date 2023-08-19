package com.business.complementaryservices.facade;

import com.business.complementaryservices.dto.RoomTypeDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface RoomServicesFacade {

    List<RoomTypeDto> getAllRoomTypes() throws JsonProcessingException;

}
