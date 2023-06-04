package com.example.BookerRest.repository;

import com.example.BookerRest.model.Room;
import com.example.BookerRest.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    List<Room> findByRoomNumber(String roomNumber);
    List<Room> findByFloorNumber(Integer floorNumber);
    List<Room> findByPricePerNight(Double pricePerNight);
    List<Room> findByRoomTypeId(RoomType roomTypeId);
}
