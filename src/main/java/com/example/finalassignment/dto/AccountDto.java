package com.example.finalassignment.dto;

import com.example.finalassignment.model.Appointment;
import com.example.finalassignment.model.FileUpload;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class AccountDto {

    public String username;

    public Long id;

    @Size(min=3, max=50)
    public String firstName;
    public String lastName;
    public String address;
    public String zipCode;

    @Email
    public String email;
    public Long telephoneNumber;

    @JsonIncludeProperties({"id", "subject", "appointmentDate", "appointmentTime"})
    public List<Appointment> appointments;

    public List<FileUpload> fileUploads;
    public List<String> authorities;

    public AccountDto() {
    }

    public AccountDto(String username, Long id, String firstName, String lastName, String address, String zipCode, String email, Long telephoneNumber, List<Appointment> appointments, List<FileUpload> fileUploads, List<String> authorities) {
        this.username = username;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.zipCode = zipCode;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.appointments = appointments;
        this.fileUploads = fileUploads;
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        this.email= email;
    }

    public Long getTelephoneNumber() {
        return telephoneNumber;
    }
    public void setTelephoneNumber(Long telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<FileUpload> getFileUploads() {
        return fileUploads;
    }

    public void setFileUploads(List<FileUpload> fileUploads) {
        this.fileUploads = fileUploads;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDto that = (AccountDto) o;
        return Objects.equals(username, that.username) && Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(address, that.address) && Objects.equals(zipCode, that.zipCode) && Objects.equals(email, that.email) && Objects.equals(telephoneNumber, that.telephoneNumber) && Objects.equals(appointments, that.appointments) && Objects.equals(fileUploads, that.fileUploads) && Objects.equals(authorities, that.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, id, firstName, lastName, address, zipCode, email, telephoneNumber, appointments, fileUploads, authorities);
    }
}
