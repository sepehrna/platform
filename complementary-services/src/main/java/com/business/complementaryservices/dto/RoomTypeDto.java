package com.business.complementaryservices.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

public class RoomTypeDto {
    @JsonProperty(required = false)
    private Integer room_type_id;
    private String name;
    private String description;
    @JsonDeserialize(using = CustomPriceDeserializer.class)
    private Float price;
    @JsonProperty(required = false)
    private List<RoomDto> rooms;

    public Integer getRoom_type_id() {
        return room_type_id;
    }

    public void setRoom_type_id(Integer room_type_id) {
        this.room_type_id = room_type_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<RoomDto> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomDto> rooms) {
        this.rooms = rooms;
    }
}
