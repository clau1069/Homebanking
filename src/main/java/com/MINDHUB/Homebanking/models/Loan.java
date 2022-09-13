package com.MINDHUB.Homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Loan {
    //++PROPIEDADES .
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String Name;
    private double maxAmount;
    private double percentaje;
    @ElementCollection
    private List<Integer> payments;  //?propiedad multivaluada, sigue siendo una propiedad simple-
    @OneToMany(mappedBy = "loan", fetch = FetchType.EAGER)
    private Set<ClientLoan> clientLoans = new HashSet<>();

    //++CONSTRUCTORES .
    public Loan() {
    }

    public Loan(String name, double maxAmount, double percentaje, List<Integer> payments) {
        Name = name;
        this.maxAmount = maxAmount;
        this.percentaje = percentaje;
        this.payments = payments;
    }

//++GETTERS &SETTERS .

    public long getId() {
        return id;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public double getMaxAmount() {
        return maxAmount;
    }
    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public double getPercentaje() {
        return percentaje;
    }

    public void setPercentaje(double percentaje) {
        this.percentaje = percentaje;
    }

    public List<Integer> getPayments() {
        return payments;
    }
    public void setPayments(List<Integer> payments) {
        this.payments = payments;
    }

    public Set<ClientLoan> getClientLoans() {
        return clientLoans;
    }
}
