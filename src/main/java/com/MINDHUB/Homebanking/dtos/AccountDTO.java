package com.MINDHUB.Homebanking.dtos;

import com.MINDHUB.Homebanking.models.Account;
import com.MINDHUB.Homebanking.models.AccountType;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountDTO {
    private long id;
    private String number;
    private LocalDateTime creationDate;
    private double balance;
    private Set<TransactionDTO> transactions;
    private Boolean status;
    private AccountType accountType;

    public long getId() {return id;}
    public String getNumber() {return number;}
    public LocalDateTime getCreationDate() {return creationDate;}
    public double getBalance() {return balance;}
    public Set<TransactionDTO> getTransactions() {
        return transactions;
    }
    public Boolean getStatus() {
        return status;
    }
    public AccountType getAccountType() {
        return accountType;
    }

    public AccountDTO() {
    }
    public AccountDTO(Account account) {
        this.id=account.getId();
        this.number= account.getNumber();
        this.creationDate=account.getCreationDate();
        this.balance=account.getBalance();
        this.transactions=account.getTransactions().stream().map(TransactionDTO::new).collect(Collectors.toSet());
        this.status=account.getStatus();
        this.accountType=account.getAccountType();
    }
}
