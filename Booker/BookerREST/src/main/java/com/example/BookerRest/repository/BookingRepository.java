package com.example.BookerRest.repository;

import com.example.BookerRest.model.Booking;
import com.example.BookerRest.model.Client;
import com.example.BookerRest.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByRoomId(Room roomId);
    List<Booking> findByClientId(Client clientId);
    List<Booking> findByCheckInDate(LocalDateTime checkInDate);
    List<Booking> findByCheckOutDate(LocalDateTime checkOutDate);
    List<Booking> findByTotalCost(Double totalCost);
}
