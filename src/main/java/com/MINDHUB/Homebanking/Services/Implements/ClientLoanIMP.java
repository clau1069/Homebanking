package com.MINDHUB.Homebanking.Services.Implements;

import com.MINDHUB.Homebanking.Services.ClientLoanService;
import com.MINDHUB.Homebanking.models.ClientLoan;
import com.MINDHUB.Homebanking.repositories.ClientLoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientLoanIMP implements ClientLoanService {
    @Autowired
    ClientLoanRepository clientLoanRepository;
    @Override
    public void save(ClientLoan clientLoan) {
        clientLoanRepository.save(clientLoan);
    }
}
