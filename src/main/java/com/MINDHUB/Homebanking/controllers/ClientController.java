package com.MINDHUB.Homebanking.controllers;
import com.MINDHUB.Homebanking.models.Client;
import com.MINDHUB.Homebanking.repositories.ClientRepository;
import com.MINDHUB.Homebanking.dtos.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired //genera la instancia repositorio
    private ClientRepository clientRepository;

    @RequestMapping("/clients") //PETICIÓN ASOCIADA A UNA  URL
    public List<ClientDTO> getClients(){ // cuando se realiza la petición, se ejecuta el método
        // (el método ya no me retorna una lista de Clientes, sino una lista de ClientesDTO
    return clientRepository.findAll().stream().map(ClientDTO::new).collect(Collectors.toList());
    //La función stream permite procesar y transformar (con map) cada elemento de la lista que retorna findAll()
    }
    @RequestMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable Long id){
        return new ClientDTO(clientRepository.findById(id).orElse(null));
    }
    @Autowired
    private PasswordEncoder passwordEncoder;
    @RequestMapping(path = "/clients", method = RequestMethod.POST)
    public ResponseEntity<Object> register(
            @RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String email, @RequestParam String password) {
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        if (clientRepository.findByEmail(email) !=  null) {
            return new ResponseEntity<>("Email already in use", HttpStatus.FORBIDDEN);
        }
        clientRepository.save(new Client(firstName, lastName, email, passwordEncoder.encode(password)));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @RequestMapping("/clients/current")
    public ClientDTO getClientDTO(Authentication authentication){
        return new ClientDTO(clientRepository.findByEmail(authentication.getName()));
    }
}
