package com.example.finalassignment.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Account {
    @Id
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    private String firstName;
    private String lastName;
    private String address;
    private String emailAddress;
    private Long telephoneNumber;

    @OneToMany(mappedBy = "account")
    private List<Appointment> appointment;
    @OneToMany(mappedBy = "account")
    private List<FileUpload> fileUpload;
    @OneToOne(mappedBy = "account")
    private User user;



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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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


