package com.example.finalassignment.dto;

import com.example.finalassignment.model.Appointment;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class UserDto {
    @NotBlank
    @Size(min=3, max=50)
    public String firstName;
    public String lastName;
    public String address;
    @Email
    public String emailAddress;
    public Long telephoneNumber;

    @JsonIncludeProperties({"subject", "appointmentDate", "appointmentTime"})
    private List<Appointment> appointments;
}
