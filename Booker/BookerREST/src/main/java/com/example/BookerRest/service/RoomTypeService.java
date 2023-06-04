package com.example.BookerRest.service;

import com.example.BookerRest.model.RoomType;
import com.example.BookerRest.repository.RoomTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class RoomTypeService {
    @Autowired
    private RoomTypeRepository roomTypeRepository;

    public List<RoomType> getAllRoomTypes(){
        return roomTypeRepository.findAll();
    }

    public List<RoomType> getRoomTypeByName(String name){
        return roomTypeRepository.findByName(name);
    }

    public void saveRoomType(RoomType roomType){
        roomTypeRepository.save(roomType);
    }

    public void deleteRoomType(Integer id){
        roomTypeRepository.deleteById(id);
    }

    public RoomType getRoomTypeById(Integer id){
        return roomTypeRepository.findById(id).orElse(null);
    }
}
