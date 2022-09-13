package com.MINDHUB.Homebanking.Services;

import com.MINDHUB.Homebanking.models.Account;
import com.MINDHUB.Homebanking.models.Client;

import java.util.List;

public interface AccountService {
    public List<Account> findAll();
    public Account findById(long id);
    public Account findByNumber(String number);
    public void save(Account account);
}
