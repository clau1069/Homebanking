package com.MINDHUB.Homebanking.Services;

import com.MINDHUB.Homebanking.models.Card;

public interface CardService {
    public void save(Card card);
    public void deleteCard(long id);
    public Card findById(long id);
    public Card findByNumber(String number);
}
