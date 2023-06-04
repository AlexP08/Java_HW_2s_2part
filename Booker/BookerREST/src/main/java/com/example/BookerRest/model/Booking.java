package com.example.BookerRest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Entity(name="booking")
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="roomId", nullable = false)
    private Room roomId;

    @ManyToOne
    @JoinColumn(name="clientId", nullable = false)
    private Client clientId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime checkInDate, checkOutDate;

    private Double totalCost;

    @ManyToMany
    @JoinTable(name = "bookingAdditionalService",
            joinColumns = {
                    @JoinColumn(name = "bookingId", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "additionalServiceId", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Set<ExtraService> additionalServiceSet;

    public void updateBooking(Booking booking){
        if(booking.roomId != null){
            this.roomId = booking.roomId;
        }
        if(booking.clientId != null){
            this.clientId = booking.clientId;
        }
        if(booking.checkInDate != null){
            this.checkInDate = booking.checkInDate;
        }
        if(booking.checkOutDate != null){
            this.checkOutDate = booking.checkOutDate;
        }
        if(booking.totalCost != null){
            this.totalCost = booking.totalCost;
        }
    }

}
