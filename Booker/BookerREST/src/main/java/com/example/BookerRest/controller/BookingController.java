package com.example.BookerRest.controller;

import com.example.BookerRest.model.Booking;
import com.example.BookerRest.model.Client;
import com.example.BookerRest.model.Room;
import com.example.BookerRest.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping("")
    public List<Booking> getAllBookings(){
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> get(@PathVariable Integer id){
        try{
            Booking booking = bookingService.getBookingById(id);
            return new ResponseEntity<Booking>(booking, HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<Booking>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/room-id/{roomId}")
    public List<Booking> getByRoomId(@PathVariable Room roomId){
        return bookingService.getBookingByRoomId(roomId);
    }

    @GetMapping("/client-id/{clientId}")
    public List<Booking> getByClientId(@PathVariable Client clientId){
        return bookingService.getBookingByClientId(clientId);
    }

    @GetMapping("/check-in-date/{checkInDate}")
    public List<Booking> getByCheckInDate(@PathVariable String checkInDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime _checkInDate = LocalDateTime.parse(checkInDate, formatter);
        return bookingService.getBookingByCheckInDate(_checkInDate);
    }

    @GetMapping("/check-out-date/{checkOutDate}")
    public List<Booking> getByCheckOutDate(@PathVariable String checkOutDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime _checkOutDate = LocalDateTime.parse(checkOutDate, formatter);
        return bookingService.getBookingByCheckOutDate(_checkOutDate);
    }

    @GetMapping("/total-cost/{totalCost}")
    public List<Booking> getByPricePerNight(@PathVariable Double totalCost){
        return bookingService.getBookingByTotalCost(totalCost);
    }

    @PostMapping("/")
    public void add(@RequestBody Booking booking){
        bookingService.saveBooking(booking);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        bookingService.deleteBooking(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Booking booking, @PathVariable Integer id){
        try{
            Booking baseBooking = bookingService.getBookingById(id);
            baseBooking.updateBooking(booking);
            bookingService.saveBooking(baseBooking);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
