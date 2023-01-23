package com.example.finalassignment.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentDto {
    @NotBlank
    public String subject;
    @NotNull
    public LocalDate appointmentDate;
    public LocalTime appointmentTime;
}
