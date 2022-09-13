package com.MINDHUB.Homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Card {
    //++propiedades
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String cardholder;
    private String number;
    private CardType type;
    private CardColor color;
    private Integer cvv;
    private LocalDate trhuDate;
    private LocalDate fromDate;
    @ManyToOne
    private Client client;


    //++constructores

    public Card() {
    }

    public Card(String cardholder, String number, CardType type, CardColor color, Integer cvv, LocalDate trhuDate, LocalDate fromDate, Client client) {
        this.cardholder = cardholder;
        this.number = number;
        this.type = type;
        this.color = color;
        this.cvv = cvv;
        this.fromDate = fromDate;
        this.trhuDate = trhuDate;
        this.client = client;
    }
    //++getter & setters


    public Long getId() {
        return id;
    }
    public String getCardholder() {
        return cardholder;
    }
    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public CardType getType() {
        return type;
    }
    public void setType(CardType type) {
        this.type = type;
    }
    public CardColor getColor() {
        return color;
    }
    public void setColor(CardColor color) {
        this.color = color;
    }
    public Integer getCvv() {
        return cvv;
    }
    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }
    public LocalDate getTrhuDate() {
        return trhuDate;
    }
    public void setTrhuDate(LocalDate trhuDate) {
        this.trhuDate = trhuDate;
    }
    public LocalDate getFromDate() {
        return fromDate;
    }
    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
}
