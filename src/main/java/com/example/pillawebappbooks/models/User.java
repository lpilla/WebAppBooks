package com.example.pillawebappbooks.models;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Entity
@Table(name = "Utenti")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Size(min=4, max=15, message = "Nome deve esser tra 4 e 15 caratteri")
    @NotNull(message = "Nome deve esser inserito")
    //@Column(name="firstname")
    String nome;

    @Size(min=4, max=15, message = "Cognome deve esser tra 4 e 15 caratteri")
    @NotNull(message = "Cognome deve esser inserito")
    String cognome;

    @Size(min=5, max=25, message = "Email deve esser tra 5 e 25 caratteri")
    @Email(message = "Email inserita non valida")
    @NotNull(message = "Email deve esser inserito")
    String email;

    @Size(min=4, max=10, message = "Username deve esser tra 4 e 10 caratteri")
    @NotNull(message = "Username deve esser inserito")
    String username;

    @Size(min=6, max=25, message = "Password deve esser tra 6 e 25 caratteri")
    @NotNull(message = "Password deve esser inserito")
    String password;

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    Set<Book> books = new HashSet<>();

    //il costruttore di default è NECESSARIO
    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(Long id, String username, String password, String email, String nome, String cognome) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
    }

    public User(String username, String password, String email, String nome, String cognome) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", nome=" + nome
                + ", cognome=" + cognome + "]";
    }
}
