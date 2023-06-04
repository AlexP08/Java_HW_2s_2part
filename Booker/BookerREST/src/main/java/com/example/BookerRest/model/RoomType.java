package com.example.BookerRest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity(name="roomType")
@Data
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "roomTypeId")
    @JsonIgnore
    private Set<Room> rooms;

    public void updateRoomType(RoomType roomType){
        if(roomType.name != null){
            this.name = roomType.name;
        }
    }
}
