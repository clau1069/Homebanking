package com.MINDHUB.Homebanking.Services.Implements;

import com.MINDHUB.Homebanking.Services.TransactionService;
import com.MINDHUB.Homebanking.models.Transaction;
import com.MINDHUB.Homebanking.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TransactionServiceIMPL implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public void deleteAll(Set<Transaction> transactions) {
        transactionRepository.deleteAll(transactions);
    }


}
