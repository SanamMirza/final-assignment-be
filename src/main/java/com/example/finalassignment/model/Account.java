package com.example.finalassignment.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name= "accounts")
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String firstName;
    private String lastName;
    private String address;
    @Column(unique = true)
    private String email;
    private Long telephoneNumber;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Appointment> appointment;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<FileUpload> fileUpload;
    @OneToOne(mappedBy = "account")
    private User user;

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(Long telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() { return username;}


}


