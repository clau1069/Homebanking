package com.MINDHUB.Homebanking.controllers;
import com.MINDHUB.Homebanking.Services.AccountService;
import com.MINDHUB.Homebanking.Services.ClientService;
import com.MINDHUB.Homebanking.Services.TransactionService;
import com.MINDHUB.Homebanking.dtos.CardDTO;
import com.MINDHUB.Homebanking.models.Account;
import com.MINDHUB.Homebanking.models.AccountType;
import com.MINDHUB.Homebanking.models.Client;
import com.MINDHUB.Homebanking.models.Transaction;
import com.MINDHUB.Homebanking.repositories.AccountRepository;
import com.MINDHUB.Homebanking.repositories.ClientRepository;
import com.MINDHUB.Homebanking.repositories.TransactionRepository;
import com.MINDHUB.Homebanking.dtos.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    ClientService clientService;
    @Autowired
    AccountService accountService;
    @Autowired
    TransactionService transactionService;
    @GetMapping("/accounts")
    public List<AccountDTO> getAccounts(){
        return accountService.findAll().stream().map(AccountDTO::new).collect(Collectors.toList());
    }
    @GetMapping("/accounts/{id}")
    public AccountDTO getAccount(@PathVariable Long id) {
        return new AccountDTO(accountService.findById(id));
    }
    public String createRandomNumber(){
        return "VIN"+(Math.round((Math.random()*(99999999))));
    }
    @PostMapping("/clients/current/accounts")
    public ResponseEntity<String> createAccount(Authentication authentication, AccountType accountType){
        Client client= clientService.findByEmail(authentication.getName());
        Set<Account> enabledClientAcocunts= client.getAccounts().stream().filter(account -> account.getStatus()==true).collect(Collectors.toSet());
        if(accountType.toString().isEmpty()){
            return new ResponseEntity<>("Missing Data: Account Type", HttpStatus.FORBIDDEN);
        }
        if ((enabledClientAcocunts.size())>=3){
            return new ResponseEntity<>("cannot add more than 3 accounts", HttpStatus.FORBIDDEN);
        }else{
            String number;
            do{
                number=createRandomNumber();
            }while(accountService.findByNumber(number)!=null);
            Account account= new Account(number, LocalDateTime.now(),0,true, accountType);
            client.addAccount(account);
            accountService.save(account);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

    }
    @GetMapping("/clients/current/accounts")
    public Set<AccountDTO> getAccounts(Authentication authentication){
        return clientService.findByEmail(authentication.getName()).getAccounts().stream().map(account -> new AccountDTO(account)).collect(Collectors.toSet());
    }
    @Transactional
    @RequestMapping(value = "/clients/current/accounts", method = RequestMethod.PATCH)
    public ResponseEntity<Object> removeAccount(@RequestParam long id, Authentication authentication){
        Account account= accountService.findById(id);
        Client client= clientService.findByEmail(authentication.getName());
        if(String.valueOf(id).isEmpty()){
            return new ResponseEntity<>("Missing data: id of Account", HttpStatus.FORBIDDEN);
        }
        if(accountService.findById(id)==null){
            return new ResponseEntity<>("The account doesn't exist", HttpStatus.FORBIDDEN);
        }
        if(client==null){
            return new ResponseEntity<>("The client doesn't exist", HttpStatus.FORBIDDEN);
        }
        if(!(client.getAccounts().contains(account))){
            return new ResponseEntity<>("The account does not belong to the client", HttpStatus.FORBIDDEN);
        }

        account.setStatus(false);
        accountService.save(account);
        Set<Transaction> accountTransactions= transactionService.findAll().stream().filter(transaction -> transaction.getAccount()==account).collect(Collectors.toSet());
        transactionService.deleteAll(accountTransactions);
        return new ResponseEntity<>("The account was disabled",HttpStatus.OK);
    }
}
