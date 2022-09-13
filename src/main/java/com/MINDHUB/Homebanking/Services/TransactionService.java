package com.MINDHUB.Homebanking.Services;

import com.MINDHUB.Homebanking.models.Transaction;

import java.util.List;
import java.util.Set;

public interface TransactionService {
    public void save(Transaction transaction);
    public List<Transaction> findAll();
    public void deleteAll(Set<Transaction> transactions);
}
