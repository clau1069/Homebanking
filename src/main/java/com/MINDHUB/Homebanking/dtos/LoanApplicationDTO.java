package com.MINDHUB.Homebanking.dtos;

import java.util.List;

public class LoanApplicationDTO {
    private long idLoan;
    private double amount;
    private Integer payments;
    private String numberAccount;
    private String loanName;
    private double maxAmount;
    private double percentage;
    private List<Integer> paymentsList;

    public long getIdLoan() {
        return idLoan;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public Integer getPayments() {
        return payments;
    }
    public void setPayments(Integer payments) {
        this.payments = payments;
    }
    public String getNumberAccount() {
        return numberAccount;
    }
    public void setNumberAccount(String numberAccount) {
        this.numberAccount = numberAccount;
    }

    public String getLoanName() {
        return loanName;
    }
    public double getMaxAmount() {
        return maxAmount;
    }
    public double getPercentage() {
        return percentage;
    }
    public List<Integer> getPaymentsList() {
        return paymentsList;
    }
}
