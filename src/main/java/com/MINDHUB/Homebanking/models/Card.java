package com.MINDHUB.Homebanking.models;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Card {
    //++propiedades
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String cardholder;
    private String number;
    private CardType type;
    private CardColor color;
    private Integer cvv;
    private LocalDate trhuDate;
    private LocalDate fromDate;
    private Boolean status = true;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Client client;


    //++constructores

    public Card() {
    }

    public Card(String cardholder, String number, CardType type, CardColor color, Integer cvv, LocalDate trhuDate, LocalDate fromDate, Boolean status, Client client) {
        this.cardholder = cardholder;
        this.number = number;
        this.type = type;
        this.color = color;
        this.cvv = cvv;
        this.trhuDate = trhuDate;
        this.fromDate = fromDate;
        this.status = status;
        this.client = client;
    }

//++getter & setters


    public long getId() {
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
    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
}
