package com.MINDHUB.Homebanking.repositories;

import com.MINDHUB.Homebanking.models.Client;
import com.MINDHUB.Homebanking.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Long> {
    public Client findByEmail (String email);
}
