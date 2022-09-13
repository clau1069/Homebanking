package com.MINDHUB.Homebanking.repositories;

import com.MINDHUB.Homebanking.models.Card;
import com.MINDHUB.Homebanking.models.CardColor;
import com.MINDHUB.Homebanking.models.CardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;

@RepositoryRestResource
public interface CardRepository extends JpaRepository <Card,Long> {
    public Card findByNumber(String number);
}
