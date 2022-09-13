package com.MINDHUB.Homebanking.dtos;

import com.MINDHUB.Homebanking.models.Client;
import com.MINDHUB.Homebanking.models.ClientLoan;
import com.MINDHUB.Homebanking.models.Loan;

public class ClientLoanDTO {
    //++propiedades
    private long id;
    private double Amount;
    private Integer payments;
    private Long loanId;
    private Long idClient;
    private String name;
    //++constructor

    public ClientLoanDTO() {
    }
    public ClientLoanDTO(ClientLoan clientLoan) {
        this.id = clientLoan.getId();
        this.Amount = clientLoan.getAmount();
        this.payments = clientLoan.getPayments();
        this.loanId = clientLoan.getLoan().getId();
        this.idClient= clientLoan.getClient().getId();
        this.name = clientLoan.getLoan().getName();
    }

    public long getId() {
        return id;
    }
    public double getAmount() {
        return Amount;
    }
    public Integer getPayments() {
        return payments;
    }
    public Long getIdLoan() {
        return loanId;
    }
    public Long getIdClient() {
        return idClient;
    }
    public String getName() {
        return name;
    }
}
