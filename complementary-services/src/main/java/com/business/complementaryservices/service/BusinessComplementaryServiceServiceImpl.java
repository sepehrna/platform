package com.business.complementaryservices.service;

import com.business.complementaryservices.domain.BusinessComplementaryService;
import com.business.complementaryservices.dto.BookingDto;
import com.business.complementaryservices.dto.RoomTypeDto;
import com.business.complementaryservices.dto.RoomTypeSelectorDto;
import com.business.complementaryservices.facade.BookingServicesFacade;
import com.business.complementaryservices.facade.RoomServicesFacade;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusinessComplementaryServiceServiceImpl implements BusinessComplementaryService {

    private final BookingServicesFacade bookingServicesFacade;
    private final RoomServicesFacade roomServicesFacade;

    @Autowired
    public BusinessComplementaryServiceServiceImpl(BookingServicesFacade bookingServicesFacade, RoomServicesFacade roomServicesFacade) {
        this.bookingServicesFacade = bookingServicesFacade;
        this.roomServicesFacade = roomServicesFacade;
    }

    @Override
    public List<RoomTypeSelectorDto> getAvailableRoomTypes(LocalDate checkInDate) throws JsonProcessingException {

        List<BookingDto> fetchedBookings = bookingServicesFacade.getBookingsByCheckInDate(checkInDate);
        List<RoomTypeDto> availableRoomTypes = roomServicesFacade.getAllRoomTypes();

        availableRoomTypes = availableRoomTypes.stream()
                .filter(roomTypeDto ->
                        roomTypeDto.getRooms() != null
                        && !roomTypeDto.getRooms().isEmpty()
                        && fetchedBookings
                        .stream()
                        .filter(bookingDto -> bookingDto.getRoomTypeId().equals(roomTypeDto.getRoom_type_id()))
                        .count() < (long) roomTypeDto.getRooms().size()
                )
                .collect(Collectors.toList());

        List<RoomTypeSelectorDto> availableRoomTypesSelectors = new ArrayList<>();
        availableRoomTypes.forEach(roomTypeDto -> {
            RoomTypeSelectorDto roomTypeSelector = new RoomTypeSelectorDto();
            roomTypeSelector.setName(roomTypeDto.getName());
            roomTypeSelector.setPrice(roomTypeDto.getPrice());
            roomTypeSelector.setRoom_type_id(roomTypeDto.getRoom_type_id());
            roomTypeSelector.setDescription(roomTypeDto.getDescription());
            availableRoomTypesSelectors.add(roomTypeSelector);
        });

        return availableRoomTypesSelectors;
    }
}
