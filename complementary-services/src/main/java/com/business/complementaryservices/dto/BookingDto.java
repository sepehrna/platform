package com.business.complementaryservices.dto;

import java.sql.Date;
import java.time.LocalDate;

public class BookingDto {
    private Integer bookingId;
    private Integer roomTypeId;
    private LocalDate checkInDate;
    private LocalDate getCheckOutDate;
    private String status;

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Integer roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getGetCheckOutDate() {
        return getCheckOutDate;
    }

    public void setGetCheckOutDate(LocalDate getCheckOutDate) {
        this.getCheckOutDate = getCheckOutDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
