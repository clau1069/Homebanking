package com.MINDHUB.Homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity

public class Transaction {
    //++propiedades
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long Id;
    private TransactionType type;
    private Double amount;
    private String description;
    private LocalDateTime CreationDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="account_id")
    private Account account;


    //++ constructores .

    public Transaction() {
    }

    public Transaction(TransactionType type, Double amount, String description, LocalDateTime creationDate, Account account) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        CreationDate = creationDate;
        this.account = account;
    }

    //++ getters & setters .


    public long getId() {
        return Id;
    }
    public TransactionType getType() {
        return type;
    }
    public void setType(TransactionType type) {
        this.type = type;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDateTime getCreationDate() {
        return CreationDate;
    }
    public void setCreationDate(LocalDateTime creationDate) {
        CreationDate = creationDate;
    }
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
}
