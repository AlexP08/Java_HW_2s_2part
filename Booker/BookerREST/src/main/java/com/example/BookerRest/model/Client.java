package com.example.BookerRest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Entity(name="client")
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String lastName, firstName, middleName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "clientId")
    @JsonIgnore
    private Set<Booking> bookingSetClient;

    public void updateClient(Client client){
        if(client.lastName != null){
            this.lastName = client.lastName;
        }
        if(client.firstName != null){
            this.firstName = client.firstName;
        }
        if(client.middleName != null){
            this.middleName = client.middleName;
        }
        if(client.dateOfBirth != null){
            this.dateOfBirth = client.dateOfBirth;
        }
    }
}
