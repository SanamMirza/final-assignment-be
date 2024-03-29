package com.example.finalassignment.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name= "users")
public class User {
    @Id
    private String username;
    @Column(nullable = false, length = 255)
    private String password;
    @Column(unique = true)
    private String email;

    @OneToMany(
            targetEntity = Authority.class,
            mappedBy = "username",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<Authority> authorities = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Account account;

    public String getUsername() { return username; }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email;}

    public Set<Authority> getAuthorities() { return authorities; }
    public void addAuthority(Authority authority) {
        this.authorities.add(authority);
    }
    public void removeAuthority(Authority authority) {
        this.authorities.remove(authority);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) { this.account = account;

    }
}
