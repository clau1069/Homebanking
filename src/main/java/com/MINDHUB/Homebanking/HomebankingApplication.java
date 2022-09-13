package com.MINDHUB.Homebanking;

import com.MINDHUB.Homebanking.repositories.*;
import com.MINDHUB.Homebanking.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


import static com.MINDHUB.Homebanking.models.AccountType.CURRENT;
import static com.MINDHUB.Homebanking.models.AccountType.SAVING;
import static com.MINDHUB.Homebanking.models.TransactionType.CREDIT;
import static com.MINDHUB.Homebanking.models.TransactionType.DEBIT;


@SpringBootApplication
public class HomebankingApplication {
	@Autowired // para que Spring inyecte el objeto PasswordEncoder que se crea con el @Bean en la clase WebAuthentication
	private PasswordEncoder passwordEnconder;
	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}

	@Bean //para que funcione en el aplicativo , es el PRIMER código que se ejecuta
	//el primer metodo que se ejecuta
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientloanRepository, CardRepository cardRepository) {
		return (args) -> {
			LocalDateTime creationDate = LocalDateTime.now();
			LocalDate actualDate= LocalDate.now();
			LocalDate actualPlus5= LocalDate.now().plusYears(5);
			LocalDate actualPlus6= LocalDate.now().plusYears(6);
			//++clientes
			// save a couple of customers

			Client client1= new Client("Melba", "Morel", "melba@mindhub.com","https://img.freepik.com/foto-gratis/mujer-hermosa-joven-mirando-camara-chica-moda-verano-casual-camiseta-blanca-pantalones-cortos-hembra-positiva-muestra-emociones-faciales-modelo-divertido-aislado-amarillo_158538-15796.jpg?w=2000", passwordEnconder.encode("4545"));
			Client client2= new Client("Claudia", "Ruiz", "clau@hotmail.com", "https://img.freepik.com/foto-gratis/mujer-positiva-sonriendo-modelo-divertido-que-presenta-cerca-pared-rosada-estudio_158538-3433.jpg?w=2000", passwordEnconder.encode("clau4545ruiz"));
			//++clienteADMIN
			Client admin1= new Client("Claudia", "Ruiz", "admin@hotmail.com", passwordEnconder.encode("4545"));
			//++cuentas
			Account account1 = new Account ("VIN001", creationDate, 5000.00, true, CURRENT);
			Account account2 = new Account ("VIN002", creationDate.plusDays(1), 7500.00, true, SAVING);
			Account account3= new Account("VIN008", creationDate, 4000.00, true, SAVING);

			//++transacciones

			Transaction transaction1= new Transaction(DEBIT, -500.00, "Shop Mercado Libre", creationDate, account3);
			Transaction transaction2= new Transaction(CREDIT, 2500.00, "Transfer Gustabo Hernán", creationDate, account3);
			Transaction transaction3= new Transaction (CREDIT, 400.00, "Transfer Clienta María", creationDate, account1);
			Transaction transaction4= new Transaction (CREDIT, 7000.00, "Transfer Julieta Milagros", creationDate, account1);
			Transaction transaction5= new Transaction (CREDIT, 420.20, "Transfer Julián Barrientos", creationDate, account2);
			Transaction transaction6= new Transaction (DEBIT, -2000.20, "Shop Batistella", creationDate, account2);
			Transaction transaction7= new Transaction(DEBIT, -2400.00, "School", creationDate, account1);
			Transaction transaction8 =new Transaction(CREDIT, 9078.80, "Bank", creationDate, account2);

			//++Préstamos
			Loan loan1= new Loan("Mortage", 500000, 0.4, List.of(12, 24, 36, 48, 60));
			Loan loan2= new Loan("Personal", 100000,0.2, List.of(6, 12, 24));
			Loan loan3= new Loan("Automotive", 300000, 0.26, List.of(6, 12, 24, 36));

			//++ClientLoans
			ClientLoan prestamo1= new ClientLoan(400000, 60, client1, loan1);
			ClientLoan prestamo2= new ClientLoan(50000, 12, client1, loan2);
			ClientLoan prestamo3= new ClientLoan(100000, 24, client2, loan2);
			ClientLoan prestamo4= new ClientLoan(200000, 36, client2, loan3);

			//++Cards
			Card card1= new Card("Melba Morel", "1255-5647-0877-4854", CardType.DEBIT, CardColor.GOLD, 224, actualPlus5, actualDate, true, client1);
			Card card4= new Card("Melba Morel", "7777-5447-0007-0004", CardType.DEBIT, CardColor.TITANIUM, 224, LocalDate.of(2020,8,8), LocalDate.of(2015,8,8), true, client1);
			//Card card5= new Card("Melba Morel", "4894-4015-6456-8888", CardType.CREDIT, CardColor.GOLD, 224, LocalDate.of(2020,8,8), LocalDate.of(2015,8,8), true, client1);
			Card card2= new Card("Melba Morel", "7774-7844-0004-6635", CardType.CREDIT, CardColor.TITANIUM, 203, actualPlus6, actualDate, true, client1);
			Card card3= new Card("Claudia Ruiz", "0071-4442-0560-0028", CardType.CREDIT, CardColor.SILVER, 722, actualPlus6, actualDate, true, client2);


			client1.addAccount(account1);
			client1.addAccount(account2);
			client2.addAccount(account3);
			clientRepository.save (client1);
			clientRepository.save(client2);
			clientRepository.save(admin1);
			accountRepository.save(account1);
			accountRepository.save(account2);
			accountRepository.save(account3);
			transactionRepository.save(transaction1);
			transactionRepository.save(transaction2);
			transactionRepository.save(transaction3);
			transactionRepository.save(transaction4);
			transactionRepository.save(transaction5);
			transactionRepository.save(transaction6);
			transactionRepository.save(transaction7);
			transactionRepository.save(transaction8);
			loanRepository.save(loan1);
			loanRepository.save(loan2);
			loanRepository.save(loan3);
			clientloanRepository.save(prestamo1);
			clientloanRepository.save(prestamo2);
			clientloanRepository.save(prestamo3);
			clientloanRepository.save(prestamo4);
			cardRepository.save(card1);
			cardRepository.save(card2);
			cardRepository.save(card3);
			cardRepository.save(card4);
			//cardRepository.save(card5);





		};
	}
}
