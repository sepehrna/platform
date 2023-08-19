package com.business.complementaryservices.facade;

import com.business.complementaryservices.dto.BookingDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.time.LocalDate;
import java.util.List;

public interface BookingServicesFacade {

    List<BookingDto> getBookingsByCheckInDate(LocalDate checkInDate) throws JsonProcessingException;

}
