package com.MINDHUB.Homebanking.dtos;

import com.MINDHUB.Homebanking.models.Loan;
import java.util.List;

public class LoanDTO {
    private long id;
    private String name;
    private double maxAmount;
    private List<Integer> payments;
    private double percentaje;

    public LoanDTO() {
    }
    public LoanDTO(Loan loan) {
        this.id=loan.getId();
        this.name=loan.getName();
        this.maxAmount=loan.getMaxAmount();
        this.payments=loan.getPayments();
        this.percentaje= loan.getPercentaje();
    }
    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getMaxAmount() {
        return maxAmount;
    }
    public double getPercentaje() {
        return percentaje;
    }
    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }
    public List<Integer> getPayments() {
        return payments;
    }
    public void setPayments(List<Integer> payments) {
        this.payments = payments;
    }
}
