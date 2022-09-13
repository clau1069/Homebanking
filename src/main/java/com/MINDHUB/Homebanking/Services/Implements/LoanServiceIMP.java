package com.MINDHUB.Homebanking.Services.Implements;

import com.MINDHUB.Homebanking.Services.LoanService;
import com.MINDHUB.Homebanking.models.Loan;
import com.MINDHUB.Homebanking.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceIMP implements LoanService {
    @Autowired
    LoanRepository loanRepository;
    @Override
    public List<Loan> findAll() {
        return loanRepository.findAll();
    }

    @Override
    public Loan findById(long id) {
        return loanRepository.findById(id);
    }

    @Override
    public void save(Loan loan) {
        loanRepository.save(loan);
    }
}
