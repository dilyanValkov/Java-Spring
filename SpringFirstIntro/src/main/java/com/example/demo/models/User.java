package com.example.demo.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Setter
@Getter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_name", unique = true)
    private String username;

    @Column
    private int age;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Account> accounts;
    public User() {
        this.accounts = new ArrayList<>();
    }
    public User(String username, int age, Account account) {
        this();
        this.username = username;
        this.age = age;
        this.accounts.add(account);
    }

    public List<Long> getAccountsId(){
        return this.accounts
                .stream()
                .map(Account::getId)
                .collect(Collectors.toList());
    }
    public void addAccount (Account account){}
    public void removeAccount (Account account){}
}
