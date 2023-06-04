package com.example.BookerRest.controller;

import com.example.BookerRest.model.Room;
import com.example.BookerRest.model.RoomType;
import com.example.BookerRest.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("")
    public List<Room> getAllRooms(){
        return roomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> get(@PathVariable Integer id){
        try{
            Room room = roomService.getRoomById(id);
            return new ResponseEntity<Room>(room, HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<Room>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/room-number/{roomNumber}")
    public List<Room> getByRoomNumber(@PathVariable String roomNumber){
        return roomService.getRoomByRoomNumber(roomNumber);
    }

    @GetMapping("/floor-number/{floorNumber}")
    public List<Room> getByFloorNumber(@PathVariable Integer floorNumber){
        return roomService.getRoomByFloorNumber(floorNumber);
    }

    @GetMapping("/price-per-night/{pricePerNight}")
    public List<Room> getByPricePerNight(@PathVariable Double pricePerNight){
        return roomService.getRoomByPricePerNight(pricePerNight);
    }

    @GetMapping("/room-type-id/{roomTypeId}")
    public List<Room> getByRoomTypeId(@PathVariable RoomType roomTypeId){
        return roomService.getRoomByRoomTypeId(roomTypeId);
    }

    @PostMapping("/")
    public void add(@RequestBody Room room){
        roomService.saveRoom(room);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        roomService.deleteRoom(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Room room, @PathVariable Integer id){
        try{
            Room baseRoom = roomService.getRoomById(id);
            baseRoom.updateRoom(room);
            roomService.saveRoom(baseRoom);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
