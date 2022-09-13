package com.MINDHUB.Homebanking.Services.Implements;

import com.MINDHUB.Homebanking.Services.AccountService;
import com.MINDHUB.Homebanking.models.Account;
import com.MINDHUB.Homebanking.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceIMP implements AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public Account findByNumber(String number) {
        return accountRepository.findByNumber(number);
    }

    @Override
    public void save(Account account) {
    accountRepository.save(account);
    }
}
