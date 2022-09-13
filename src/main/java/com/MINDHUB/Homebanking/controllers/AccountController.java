package com.MINDHUB.Homebanking.controllers;

import com.MINDHUB.Homebanking.repositories.AccountRepository;
import com.MINDHUB.Homebanking.repositories.TransactionRepository;
import com.MINDHUB.Homebanking.dtos.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;

    @RequestMapping("/accounts")
    public List<AccountDTO> getAccounts(){
        return accountRepository.findAll().stream().map(AccountDTO::new).collect(Collectors.toList());
    }
    @RequestMapping("/accounts/{id}")
    public AccountDTO getAccount(@PathVariable Long id) {
        return new AccountDTO(accountRepository.findById(id).orElse(null));
    }
}
