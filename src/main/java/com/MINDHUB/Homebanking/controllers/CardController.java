package com.MINDHUB.Homebanking.controllers;
import com.MINDHUB.Homebanking.Services.CardService;
import com.MINDHUB.Homebanking.Services.ClientService;
import com.MINDHUB.Homebanking.Utils.CardUtils;
import com.MINDHUB.Homebanking.dtos.CardDTO;
import com.MINDHUB.Homebanking.models.Card;
import com.MINDHUB.Homebanking.models.CardColor;
import com.MINDHUB.Homebanking.models.CardType;
import com.MINDHUB.Homebanking.models.Client;
import com.MINDHUB.Homebanking.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CardController {
    @Autowired
    ClientService clientService;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    CardService cardService;

@GetMapping("/clients/current/cards")
public Set<CardDTO> getCards(Authentication authentication){
    return clientService.findByEmail(authentication.getName()).getCards().stream().map(card -> new CardDTO(card)).collect(Collectors.toSet());
}
@Transactional
@RequestMapping(value = "/clients/current/cards", method = RequestMethod.PATCH)
public ResponseEntity<Object> changeStatusDisabled(@RequestParam String number, Authentication authentication){
    Card card= cardService.findByNumber(number);
    Client client = clientService.findByEmail(authentication.getName());
    if(number.isEmpty()){
        return new ResponseEntity<>("Missing data: number", HttpStatus.FORBIDDEN);
    }
    if(cardService.findByNumber(number)==null){
        return new ResponseEntity<>("The card doesn't exist", HttpStatus.FORBIDDEN);
    }

    if(client==null){
        return new ResponseEntity<>("The client doesn't exist", HttpStatus.FORBIDDEN);
    }
    if(!(client.getCards().contains(card))){
        return new ResponseEntity<>("The card does not belong to the client", HttpStatus.FORBIDDEN);
    }
    card.setStatus(false);
    cardService.save(card);
    return new ResponseEntity<>("The card was disabled",HttpStatus.OK);
}

    @Transactional
    @PostMapping("/clients/current/cards")
    public ResponseEntity<Object> createCard(
            @RequestParam CardType type,
            @RequestParam CardColor color,
            Authentication authentication) {
        Client client = clientService.findByEmail(authentication.getName());
        Set<Card> cardsFilterByType = client.getCards().stream().filter(card -> card.getType() == type).collect(Collectors.toSet());
        //List<Card> tarjetaRepetida= clientCards.stream().filter(card -> card.getColor()==color).collect(Collectors.toList());
        Set<Card>cardsFliterByStatus= cardsFilterByType.stream().filter(card -> card.getStatus()==true).collect(Collectors.toSet());
            if ((cardsFliterByStatus.size()) >= 3 ){
                return new ResponseEntity<>("Cannot add more than 3 " + type+ " cards", HttpStatus.FORBIDDEN);
            }
            if((cardsFliterByStatus.stream().filter(card -> card.getColor().equals(color)).count())>0){
                return new ResponseEntity<>("Cannot add another "+color+ " "+type+" card", HttpStatus.FORBIDDEN);
            }
        String cardNumber;
        do{
            cardNumber = CardUtils.getCardNumber();
        }while(cardService.findByNumber(cardNumber)!=null);
         Card card = new Card(client.getFirstName()+" "+client.getLastName(), cardNumber, type, color, CardUtils.Make3numbers(),LocalDate.now().plusYears(5), LocalDate.now(),true, client);
        cardService.save(card);
        clientService.save(client);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}