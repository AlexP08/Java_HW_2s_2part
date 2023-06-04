package com.example.BookerRest.controller;
import com.example.BookerRest.model.Client;
import com.example.BookerRest.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("")
    public List<Client> getAllClients(){
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> get(@PathVariable Integer id){
        try{
            Client client = clientService.getClientById(id);
            return new ResponseEntity<Client>(client, HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/last-name/{lastName}")
    public List<Client> getByLastName(@PathVariable String lastName){
        return clientService.getClientByLastName(lastName);
    }

    @GetMapping("/first-name/{firstName}")
    public List<Client> getByFirstName(@PathVariable String firstName){
        return clientService.getClientByFirstName(firstName);
    }

    @GetMapping("/middle-name/{middleName}")
    public List<Client> getByMiddleName(@PathVariable String middleName){
        return clientService.getClientByMiddleName(middleName);
    }

    @GetMapping("/date-of-birth/{dateOfBirth}")
    public List<Client> getByDateOfBirth(@PathVariable LocalDate dateOfBirth){
        return clientService.getClientByDateOfBirth(dateOfBirth);
    }

    @PostMapping("/")
    public void add(@RequestBody Client city){
        clientService.saveClient(city);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        clientService.deleteClient(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Client client, @PathVariable Integer id){
        try{
            Client baseClient = clientService.getClientById(id);
            baseClient.updateClient(client);
            clientService.saveClient(baseClient);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
