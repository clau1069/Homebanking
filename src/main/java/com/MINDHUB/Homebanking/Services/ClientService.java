package com.MINDHUB.Homebanking.Services;
import com.MINDHUB.Homebanking.models.Client;
import java.util.List;

public interface ClientService {
    public List<Client> findAll();
    public Client findById(long id);
    public Client findByEmail(String email);
    public void save(Client client);
}
