package com.example.BookerRest.repository;

import com.example.BookerRest.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findByLastName(String lastName);
    List<Client> findByFirstName(String firstName);
    List<Client> findByMiddleName(String middleName);
    List<Client> findByDateOfBirth(LocalDate dateOfBirth);
}
