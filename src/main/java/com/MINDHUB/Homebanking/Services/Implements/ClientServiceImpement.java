package com.MINDHUB.Homebanking.Services.Implements;

import com.MINDHUB.Homebanking.Services.ClientService;
import com.MINDHUB.Homebanking.models.Client;
import com.MINDHUB.Homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpement implements ClientService {
    @Autowired
    ClientRepository clientRepository;
    @Override
    public List<Client> findAll(){
        return clientRepository.findAll();
    }
    @Override
    public Client findById(long id){
        return clientRepository.findById(id).orElse(null);
    }
    @Override
    public Client findByEmail(String email){
        return clientRepository.findByEmail(email);
    }
    @Override
    public void save(Client client){
        clientRepository.save(client);
    }
}
