package com.MINDHUB.Homebanking;

import com.MINDHUB.Homebanking.Services.AccountService;
import com.MINDHUB.Homebanking.models.*;
import com.MINDHUB.Homebanking.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

//@DataJpaTest
@SpringBootTest
@AutoConfigureTestDatabase(replace = NONE) //indican a Spring que debe escanear en busca de clases @Entity y configurar los repositorios JPA.
public class RepositoriesTest {
    @Autowired
    LoanRepository loanRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    TransactionRepository transactionRepository;

    //TEST ACCOUNTS

    @Test
    public void existAcocunts(){
        List <Account> accounts = accountRepository.findAll();
        assertThat(accounts, is(not(empty())));
    }
    @Test
    public void existNewAccout(){

        Account accountEx= new Account("58080", LocalDateTime.now(), 8000, true, AccountType.SAVING);
        accountRepository.save(accountEx);
        List <Account> accounts = accountRepository.findAll();
        assertThat(accounts, hasItem(hasProperty("number", is("58080"))));
    }
    //TEST CARDS

    @Test
    public void existAnyCard(){
        List < Card> cards= cardRepository.findAll();
    }
    @Test
    public void MelbaHasCards(){
        Client Melba= clientRepository.findByEmail("melba@mindhub.com");
        assertThat(Melba, hasProperty("cards"));
    }
    //TEST CLIENTS
    @Test
    public void existAnyClient(){
        List<Client> clients= clientRepository.findAll();
        assertThat(clients, is(not(empty())));
    }
    public void clientsHavePropiertyLastName(){
        List<Client> clients= clientRepository.findAll();
        assertThat(clients, hasProperty("lastName"));
    }
    //TEST DE LOANS
    @Test
    public void existLoans() {
        List<Loan> loans = loanRepository.findAll();
        assertThat(loans, is(not(empty())));
    }
    @Test
    public void existPersonalLoan(){
        List<Loan> loans = loanRepository.findAll();
        assertThat(loans, hasItem(hasProperty("name", is("Personal"))));

    }
    //TEST DE TRANSACTIONS
    @Test
    public void existAnyTransaction(){
        List<Transaction> transactions= transactionRepository.findAll();
        assertThat(transactions, not(hasSize(0)));
    }

}