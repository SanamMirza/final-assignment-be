package com.example.finalassignment.dto;

import com.example.finalassignment.model.Appointment;

import java.util.Objects;

public class ProductDto {

    public Long id;
    public String title;
    public Appointment appointment;

    public ProductDto() {
    }

    public ProductDto(Long id, String title, Appointment appointment) {
        this.id = id;
        this.title = title;
        this.appointment = appointment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(appointment, that.appointment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, appointment);
    }
}
