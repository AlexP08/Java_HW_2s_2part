package com.example.BookerRest.service;

import com.example.BookerRest.model.Client;
import com.example.BookerRest.repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    public List<Client> getClientByLastName(String lastName){
        return clientRepository.findByLastName(lastName);
    }

    public List<Client> getClientByFirstName(String firstName){
        return clientRepository.findByFirstName(firstName);
    }

    public List<Client> getClientByMiddleName(String middleName){
        return clientRepository.findByMiddleName(middleName);
    }

    public List<Client> getClientByDateOfBirth(LocalDate dateOfBirth){
        return clientRepository.findByDateOfBirth(dateOfBirth);
    }

    public void saveClient(Client client){
        clientRepository.save(client);
    }

    public void deleteClient(Integer id){
        clientRepository.deleteById(id);
    }

    public Client getClientById(Integer id){
        return clientRepository.findById(id).orElse(null);
    }
}
