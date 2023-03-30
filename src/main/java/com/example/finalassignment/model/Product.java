package com.example.finalassignment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name= "products")
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @JsonIgnore
    @OneToOne (mappedBy = "product")
    private Appointment appointment;

    public Product() {
    }

    public Product(Long id, String title, Appointment appointment) {
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
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(title, product.title) && Objects.equals(appointment, product.appointment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, appointment);
    }
}
