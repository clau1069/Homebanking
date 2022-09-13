package com.MINDHUB.Homebanking.Services;

import com.MINDHUB.Homebanking.models.Loan;

import java.util.List;

public interface LoanService {
    public List<Loan> findAll();
    public Loan findById(long id);
    public void save(Loan loan);
}
