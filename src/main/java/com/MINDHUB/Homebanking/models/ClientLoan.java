
package com.MINDHUB.Homebanking.models;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
@Entity
public class ClientLoan {

    //++PROPIEDADES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private double Amount;
    private Integer payments;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Client client;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="loan_id")
    private Loan loan;

    //++CONSTRUCTORES
    public ClientLoan() {
    }
    public ClientLoan(double amount, Integer payments, Client client, Loan loan) {
        Amount = amount;
        this.payments = payments;
        this.client = client;
        this.loan = loan;
    }

    //++GETTERS & SETTERS

    public long getId() {
        return id;
    }
    public double getAmount() {
        return Amount;
    }
    public void setAmount(double amount) {
        Amount = amount;
    }
    public Integer getPayments() {
        return payments;
    }
    public void setPayments(Integer payments) {
        this.payments = payments;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public Loan getLoan() {
        return loan;
    }
    public void setLoan(Loan loan) {
        this.loan = loan;
    }
}
