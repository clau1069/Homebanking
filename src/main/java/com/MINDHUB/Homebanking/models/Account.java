package com.MINDHUB.Homebanking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {
    //++propiedades
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String number;
    private LocalDateTime creationDate;
    private double balance;
    private Boolean status;
    private AccountType accountType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Client usuario;
    @OneToMany(mappedBy = "account", fetch=FetchType.EAGER)
    private Set<Transaction> transactions = new HashSet<>();

    //++constructores

    public Account() {
    }

    public Account(String number, LocalDateTime creationDate, double balance, Boolean status, AccountType accountType) {
        this.number = number;
        this.creationDate = creationDate;
        this.balance = balance;
        this.status = status;
        this.accountType = accountType;
    }

//++getters & setters

    public long getId() {return id;}
    public String getNumber() {return number;}
    public void setNumber(String number) {this.number = number;}
    public LocalDateTime getCreationDate() {return creationDate;}
    public void setCreationDate(LocalDateTime creationDate) {this.creationDate = creationDate;}
    public double getBalance() {return balance;}
    public void setBalance(double balance) {this.balance = balance;}
    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    public Set<Transaction> getTransactions() {
        return transactions;
    }
    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }
    public AccountType getAccountType() {
        return accountType;
    }
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    //! .
    @JsonIgnore
    public Client getUsuario() {return usuario;}
    //!método set usuario .
    public void setUsuario(Client usuario) {
        this.usuario = usuario;
    }
}
