package com.example.BookerRest.controller;

import com.example.BookerRest.model.RoomType;
import com.example.BookerRest.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/room-types")
public class RoomTypeController {
    @Autowired
    private RoomTypeService roomTypeService;

    @GetMapping("")
    public List<RoomType> getAllRoomTypes(){
        return roomTypeService.getAllRoomTypes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomType> get(@PathVariable Integer id){
        try{
            RoomType roomType = roomTypeService.getRoomTypeById(id);
            return new ResponseEntity<RoomType>(roomType, HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<RoomType>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/name/{name}")
    public List<RoomType> getByName(@PathVariable String name){
        return roomTypeService.getRoomTypeByName(name);
    }

    @PostMapping("/")
    public void add(@RequestBody RoomType roomType){
        roomTypeService.saveRoomType(roomType);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        roomTypeService.deleteRoomType(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody RoomType roomType, @PathVariable Integer id){
        try{
            RoomType baseRoomType = roomTypeService.getRoomTypeById(id);
            baseRoomType.updateRoomType(roomType);
            roomTypeService.saveRoomType(baseRoomType);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
