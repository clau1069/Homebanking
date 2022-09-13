package com.MINDHUB.Homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Client {
    //*++propiedades
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String profilePicture;
    private String password;
    @OneToMany(mappedBy="usuario", fetch=FetchType.EAGER)
    private Set<Account> accounts = new HashSet<>(); // hash set espacio en memoria, set crea la lista y elimina los repetidos
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<ClientLoan> clientLoans = new HashSet<>();
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Card> cards= new HashSet<>();



    //!
    //++constructores

    public Client() {
    }

    public Client(String firstName, String lastName, String email, String profilePicture, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.profilePicture = profilePicture;
        this.password = password;
    }


    public Client(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
    //++getters $ setters

    public long getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Set<Account> getAccounts() {
        return accounts;
    }
    public Set<ClientLoan> getClientLoans() {
        return clientLoans;
    }
    public Set<Card> getCards() {
        return cards;
    }

    public String getProfilePicture() {
        return profilePicture;
    }
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    //! metodo agregar cuenta
    public void addAccount(Account account) {
        account.setUsuario(this);
        accounts.add(account);
    }
}
