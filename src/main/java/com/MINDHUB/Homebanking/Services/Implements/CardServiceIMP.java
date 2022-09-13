package com.MINDHUB.Homebanking.Services.Implements;

import com.MINDHUB.Homebanking.Services.CardService;
import com.MINDHUB.Homebanking.models.Card;
import com.MINDHUB.Homebanking.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceIMP implements CardService {
    @Autowired
    CardRepository cardRepository;
    @Override
    public void save(Card card) {
        cardRepository.save(card);
    }

    @Override
    public void deleteCard(long id) {
        cardRepository.deleteById(id);
    }

    @Override
    public Card findById(long id) {
        return cardRepository.findById(id).orElse(null);
    }

    @Override
    public Card findByNumber(String number) {
        return cardRepository.findByNumber(number);
    }
}
