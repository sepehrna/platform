package com.business.complementaryservices.dto;

public class RoomDto {

    private String occupied_by;
    private Integer room_id;
    private Integer room_type_id;

    public String getOccupied_by() {
        return occupied_by;
    }

    public void setOccupied_by(String occupied_by) {
        this.occupied_by = occupied_by;
    }

    public Integer getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Integer room_id) {
        this.room_id = room_id;
    }

    public Integer getRoom_type_id() {
        return room_type_id;
    }

    public void setRoom_type_id(Integer room_type_id) {
        this.room_type_id = room_type_id;
    }
}
