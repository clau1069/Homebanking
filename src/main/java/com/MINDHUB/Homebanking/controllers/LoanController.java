package com.MINDHUB.Homebanking.controllers;
import com.MINDHUB.Homebanking.Services.*;
import com.MINDHUB.Homebanking.dtos.LoanApplicationDTO;
import com.MINDHUB.Homebanking.dtos.LoanDTO;
import com.MINDHUB.Homebanking.models.*;
import com.MINDHUB.Homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import static com.MINDHUB.Homebanking.models.TransactionType.CREDIT;

@RestController
public class LoanController {
    @Autowired
    ClientService clientService;
    @Autowired
    AccountService accountService;
    @Autowired
    LoanService loanService;
    @Autowired
    ClientLoanService clientLoanService;
    @Autowired
    TransactionService transactionService;
    @GetMapping("/api/loans")
    public List<LoanDTO> getLoans(){
    return loanService.findAll().stream().map(LoanDTO::new).collect(Collectors.toList());
    }
    @Transactional
    @PostMapping("/api/loans")
    public ResponseEntity<Object> createLoan(
            @RequestBody LoanApplicationDTO loanApplicationDTO,
            Authentication authentication
    ){
        //Verificar que los datos sean correctos, es decir no estén vacíos, que el monto no sea 0 o que las cuotas no sean 0.
    if((String.valueOf(loanApplicationDTO.getIdLoan())).isEmpty() || String.valueOf(loanApplicationDTO.getAmount()).isEmpty()||loanApplicationDTO.getAmount()==0||String.valueOf(loanApplicationDTO.getPayments()).isEmpty()||loanApplicationDTO.getPayments()==0||String.valueOf(loanApplicationDTO.getNumberAccount()).isEmpty()){
        return new ResponseEntity<>("Missing Data", HttpStatus.FORBIDDEN);
    }
    //Verificar que el préstamo exista
        Loan loan= loanService.findById(loanApplicationDTO.getIdLoan());
    if(loan==null){
        return new ResponseEntity<>("The requested loan does not exist", HttpStatus.FORBIDDEN);
    }
    //Verificar que el monto solicitado no exceda el monto máximo del préstamo

    if(loanApplicationDTO.getAmount()>loan.getMaxAmount()){
        return new ResponseEntity<>("The requested amount exceeds the amount allowed by the loan", HttpStatus.FORBIDDEN);
    }
    //Verifica que la cantidad de cuotas se encuentre entre las disponibles del préstamo
        if(!(loan.getPayments().contains(loanApplicationDTO.getPayments()))) {
            return new ResponseEntity<>("This number of payments cannot be requested", HttpStatus.FORBIDDEN);
        }
        //Verificar que la cuenta de destino exista
        Account destinyAccount =accountService.findByNumber(loanApplicationDTO.getNumberAccount());
        if(destinyAccount==null){
            return new ResponseEntity<>("The destination account does not exist", HttpStatus.FORBIDDEN);
        }
        //Verificar que la cuenta de destino pertenezca al cliente autenticado
        Client authenticatedClient =clientService.findByEmail(authentication.getName());
        List<Account> cuentaDelCliente = authenticatedClient.getAccounts().stream().filter(account -> account.getNumber().equals(loanApplicationDTO.getNumberAccount())).collect(Collectors.toList());
        if(cuentaDelCliente.size()==0){
            return new ResponseEntity<>("The destination account is not from the authenticated client", HttpStatus.FORBIDDEN);
        }
        //Se debe crear una solicitud de préstamo con el monto solicitado sumando el 20% del mismo
        clientLoanService.save(new ClientLoan(loanApplicationDTO.getAmount()*loan.getPercentaje()+loanApplicationDTO.getAmount(),loanApplicationDTO.getPayments(),authenticatedClient,loan));
        //Se debe crear una transacción “CREDIT” asociada a la cuenta de destino (el monto debe quedar positivo) con la descripción concatenando el nombre del préstamo y la frase “loan approved”
        transactionService.save(new Transaction(CREDIT, loanApplicationDTO.getAmount(), loan.getName()+" Loan approved", LocalDateTime.now(), destinyAccount));
        destinyAccount.setBalance(destinyAccount.getBalance()+loanApplicationDTO.getAmount());
        return new ResponseEntity<>("Created!", HttpStatus.CREATED);
    }
    @PostMapping("/api/loans/create")
    public ResponseEntity<Object> addNewLoan(
            Authentication authentication,
            @RequestBody LoanApplicationDTO loanApplicationDTO
    ){
        if(loanApplicationDTO.getLoanName().isEmpty()||String.valueOf(loanApplicationDTO.getMaxAmount()).isEmpty()||String.valueOf(loanApplicationDTO.getPercentage()).isEmpty()|| loanApplicationDTO.getPaymentsList().toString().isEmpty()){
            return new ResponseEntity<>("Missing Data", HttpStatus.FORBIDDEN);
        }
        if(clientService.findByEmail(authentication.getName())==null){
            return new ResponseEntity<>("Not authorized", HttpStatus.FORBIDDEN);
        }
        loanService.save(new Loan(loanApplicationDTO.getLoanName(), loanApplicationDTO.getMaxAmount(), loanApplicationDTO.getPercentage(), loanApplicationDTO.getPaymentsList()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
