package com.MINDHUB.Homebanking.controllers;

import com.MINDHUB.Homebanking.Services.AccountService;
import com.MINDHUB.Homebanking.Services.ClientService;
import com.MINDHUB.Homebanking.Services.TransactionService;
import com.MINDHUB.Homebanking.models.Account;
import com.MINDHUB.Homebanking.models.Client;
import com.MINDHUB.Homebanking.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

import java.time.LocalDateTime;

import static com.MINDHUB.Homebanking.models.TransactionType.CREDIT;
import static com.MINDHUB.Homebanking.models.TransactionType.DEBIT;

@RestController
public class TransactionController {
    @Autowired
    ClientService clientService;
    @Autowired
    AccountService accountService;
    @Autowired
    TransactionService transactionService;
    @Transactional
    @PostMapping("/api/transactions")
    public ResponseEntity<Object> createTransaction(
            Authentication authentication,
            @RequestParam Double amount,
            @RequestParam String description,
            @RequestParam String numberOrigin,
            @RequestParam String numberDestiny){
//Verificar que los parámetros no estén vacíos
        if(amount.toString().isEmpty()||description.isEmpty()||numberOrigin.isEmpty()||numberDestiny.isEmpty()) {
            return new ResponseEntity<>("Missing Data", HttpStatus.FORBIDDEN);
        }
        if(amount<=0) {
            return new ResponseEntity<>("Cannot sent $0, choose another amount :)", HttpStatus.FORBIDDEN);
        }
        //Verificar que los números de cuenta no sean iguales
        if(numberOrigin.equals(numberDestiny)){
            return new ResponseEntity<>("You cannot transfer to the same account of origin. Choose another :)", HttpStatus.FORBIDDEN);
        }
        //Verificar que exista la cuenta de origen
        Account originAccount=accountService.findByNumber(numberOrigin);
        Account destinyAccount=accountService.findByNumber(numberDestiny);
        if(originAccount==null){
            return new ResponseEntity<>("origin account does not exist :)", HttpStatus.FORBIDDEN);
        }
        //Verificar que la cuenta de origen pertenezca al cliente autenticado
        Client client= clientService.findByEmail(authentication.getName());
        if((client.getAccounts().stream().filter(account -> account.getNumber().equals(numberOrigin))).count()==0){
            return new ResponseEntity<>("the account does not belong to the authenticated client :)", HttpStatus.FORBIDDEN);
        }
        //Verificar que exista la cuenta de destino
        if(destinyAccount==null){
            return new ResponseEntity<>("Destination account does not exist. Choose another :)", HttpStatus.FORBIDDEN);
        }
        //Verificar que la cuenta de origen tenga el monto disponible
        if(originAccount.getBalance()<amount){
            return new ResponseEntity<>("There are not enough funds, please choose a lower amount :)", HttpStatus.FORBIDDEN);
        }
        Transaction transactionOrigin= new Transaction(DEBIT, -amount, description, LocalDateTime.now(), originAccount);
        Transaction transactionDestiny= new Transaction(CREDIT, amount, description, LocalDateTime.now(), destinyAccount);
        transactionService.save(transactionOrigin);
        transactionService.save(transactionDestiny);
        originAccount.setBalance(originAccount.getBalance()-amount);
        destinyAccount.setBalance(destinyAccount.getBalance()+amount);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}
