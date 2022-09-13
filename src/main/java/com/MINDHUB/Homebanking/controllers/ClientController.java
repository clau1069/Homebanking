package com.MINDHUB.Homebanking.controllers;
import com.MINDHUB.Homebanking.Services.AccountService;
import com.MINDHUB.Homebanking.Services.ClientService;
import com.MINDHUB.Homebanking.models.Account;
import com.MINDHUB.Homebanking.models.AccountType;
import com.MINDHUB.Homebanking.models.Client;
import com.MINDHUB.Homebanking.repositories.ClientRepository;
import com.MINDHUB.Homebanking.dtos.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    ClientService clientService;
    @Autowired
    AccountService accountService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/clients") //PETICIÓN ASOCIADA A UNA  URL
    public List<ClientDTO> getClients(){ // cuando se realiza la petición, se ejecuta el método
        // (el método ya no me retorna una lista de Clientes, sino una lista de ClientesDTO
    return clientService.findAll().stream().map(ClientDTO::new).collect(Collectors.toList());
    //La función stream permite procesar y transformar (con map) cada elemento de la lista que retorna findAll()
    }
    @GetMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable Long id){
        return new ClientDTO(clientService.findById(id));
    }
    public String createRandomNumber(){
        return "VIN"+(Math.round((Math.random()*(99999999))));
    }
    @PostMapping("/clients")
    public ResponseEntity<Object> register(
            @RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String email, @RequestParam String password) {
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        if (clientService.findByEmail(email) !=  null) {
            return new ResponseEntity<>("Email already in use", HttpStatus.FORBIDDEN);
        }
        Client client = new Client(firstName, lastName, email, passwordEncoder.encode(password));
        Account firstAccount= new Account(createRandomNumber(), LocalDateTime.now(), 0, true, AccountType.SAVING);
        client.addAccount(firstAccount);
        clientService.save(client);
        accountService.save(firstAccount);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/clients/current")
    public ClientDTO getClientDTO(Authentication authentication){
        return new ClientDTO(clientService.findByEmail(authentication.getName()));
    }
}
