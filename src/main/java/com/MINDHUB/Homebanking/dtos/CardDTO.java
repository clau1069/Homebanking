package com.MINDHUB.Homebanking.dtos;

import com.MINDHUB.Homebanking.models.Card;
import com.MINDHUB.Homebanking.models.CardColor;
import com.MINDHUB.Homebanking.models.CardType;

import java.time.LocalDate;

public class CardDTO {
    private long id;
    private String cardholder;
    private String number;
    private CardType type;
    private CardColor color;
    private Integer cvv;
    private LocalDate trhuDate;
    private LocalDate fromDate;
    private Boolean status;

    public CardDTO() {
    }
    public CardDTO(Card card) {
        this.id= card.getId();
        this.cardholder= card.getCardholder();
        this.number=card.getNumber();
        this.type=card.getType();
        this.color=card.getColor();
        this.cvv= card.getCvv();
        this.trhuDate=card.getTrhuDate();
        this.fromDate=card.getFromDate();
        this.status= card.getStatus();
    }

    public long getId() {
        return id;
    }
    public String getCardholder() {
        return cardholder;
    }
    public String getNumber() {
        return number;
    }
    public CardType getType() {
        return type;
    }
    public CardColor getColor() {
        return color;
    }
    public Integer getCvv() {
        return cvv;
    }
    public LocalDate getTrhuDate() {
        return trhuDate;
    }
    public LocalDate getFromDate() {
        return fromDate;
    }
    public Boolean getStatus() {
        return status;
    }
}
