package com.MINDHUB.Homebanking.dtos;

import com.MINDHUB.Homebanking.models.Transaction;
import com.MINDHUB.Homebanking.models.TransactionType;

import java.time.LocalDateTime;

public class TransactionDTO {
    //++propiedades
    private long id;
    private TransactionType type;
    private Double amount;
    private String description;
    private LocalDateTime date;

    //++ getters

    public long getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public Double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    //++constructores .


    public TransactionDTO() {
    }
    public TransactionDTO(Transaction transaction) {
        this.id= transaction.getId();
        this.type=transaction.getType();
        this.description=transaction.getDescription();
        this.amount=transaction.getAmount();
        this.date=transaction.getCreationDate();
    }

}
