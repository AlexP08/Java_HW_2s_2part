package com.example.BookerRest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity(name="room")
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String roomNumber;
    private Integer floorNumber;
    private Double pricePerNight;

    @ManyToOne
    @JoinColumn(name="roomTypeId", nullable = false)
    private RoomType roomTypeId;

    @OneToMany(mappedBy = "roomId")
    @JsonIgnore
    private Set<Booking> bookingSetRoom;

    public void updateRoom(Room room){
        if(room.roomNumber != null){
            this.roomNumber = room.roomNumber;
        }
        if(room.floorNumber != null){
            this.floorNumber = room.floorNumber;
        }
        if(room.pricePerNight != null){
            this.pricePerNight = room.pricePerNight;
        }
        if(room.roomTypeId != null){
            this.roomTypeId = room.roomTypeId;
        }
    }
}
