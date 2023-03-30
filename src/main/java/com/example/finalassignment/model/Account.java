package com.example.finalassignment.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


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
    private String zipCode;
    @Column(unique = true)
    private String email;
    private Long telephoneNumber;

    @JsonManagedReference
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Appointment> appointment;

    @JsonManagedReference
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<FileUpload> fileUpload;

    @JsonIgnore
    @OneToOne(mappedBy = "account")
    private User user;

    public Account() {
    }

    public Account(Long id, String username, String firstName, String lastName, String address, String zipCode, String email, Long telephoneNumber, List<Appointment> appointment, List<FileUpload> fileUpload, User user) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.zipCode = zipCode;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.appointment = appointment;
        this.fileUpload = fileUpload;
        this.user = user;
    }

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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
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

    public List<Appointment> getAppointment() {
        return appointment;
    }

    public void setAppointment(List<Appointment> appointment) {
        this.appointment = appointment;
    }

    public List<FileUpload> getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(List<FileUpload> fileUpload) {
        this.fileUpload = fileUpload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(username, account.username) && Objects.equals(firstName, account.firstName) && Objects.equals(lastName, account.lastName) && Objects.equals(address, account.address) && Objects.equals(zipCode, account.zipCode) && Objects.equals(email, account.email) && Objects.equals(telephoneNumber, account.telephoneNumber) && Objects.equals(appointment, account.appointment) && Objects.equals(fileUpload, account.fileUpload) && Objects.equals(user, account.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, firstName, lastName, address, zipCode, email, telephoneNumber, appointment, fileUpload, user);
    }
}


