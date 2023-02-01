package com.example.finalassignment.dto;

import com.example.finalassignment.model.Appointment;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

public class AccountDto {
    public String username;

    @Size(min=3, max=50)
    public String firstName;
    public String lastName;
    public String address;
    @Email
    public String email;
    public Long telephoneNumber;

    @JsonIncludeProperties({"subject", "appointmentDate", "appointmentTime"})
    private List<Appointment> appointments;

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

}
