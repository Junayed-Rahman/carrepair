package com.naoshin.azure.service;

import com.naoshin.azure.model.Client;
import com.naoshin.azure.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    public ClientRepository clientRepository;


//    public Client alreadyTakenAppointment(Client client1) {
//        Optional<Client>  client = clientRepository.findByNameAndPhone(client1.getName(), client1.getPhone());
//        if (client.isPresent()) {
//            Client oldClient = client.get();
//            return oldClient;
//        }
//        else {
//            return null;
//        }
//
//    }

}
