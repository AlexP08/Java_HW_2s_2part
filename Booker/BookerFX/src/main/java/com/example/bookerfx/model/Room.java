package com.example.bookerfx.model;

public class Room {

    private Integer id;
    private String roomNumber;
    private Integer floorNumber;
    private Double pricePerNight;
    private RoomType roomTypeId;

    public Room() {
    }

    public Room(String roomNumber, Integer floorNumber, Double pricePerNight, RoomType roomTypeId) {
        this.roomNumber = roomNumber;
        this.floorNumber = floorNumber;
        this.pricePerNight = pricePerNight;
        this.roomTypeId = roomTypeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public RoomType getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(RoomType roomTypeId) {
        this.roomTypeId = roomTypeId;
    }
}
