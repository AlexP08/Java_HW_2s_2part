package com.example.BookerRest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity(name="ExtraService")
@Data
public class ExtraService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private Double pricePerDay;

    @ManyToMany(mappedBy = "ExtraServiceSet")
    private Set<Booking> bookingSet;

    public void updateAdditionalService(ExtraService extraService){
        if(extraService.name != null){
            this.name = extraService.name;
        }
        if(extraService.pricePerDay != null){
            this.pricePerDay = extraService.pricePerDay;
        }
    }

}
