package com.example.bookerfx.model;

import java.time.LocalDateTime;

public class Booking {
    private Integer id;
    private Room roomId;
    private Client clientId;
    private LocalDateTime checkInDate, checkOutDate;
    private Double totalCost;

    public Booking() {

    }

    public Booking(Room roomId, Client clientId, LocalDateTime checkInDate, LocalDateTime checkOutDate, Double totalCost) {
        this.roomId = roomId;
        this.clientId = clientId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalCost = totalCost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Room getRoomId() {
        return roomId;
    }

    public void setRoomId(Room roomId) {
        this.roomId = roomId;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    public LocalDateTime getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDateTime checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDateTime getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDateTime checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }
}
