package com.example.finalassignment.model;

import javax.persistence.*;

@Entity
@Table(name= "products")
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String title;

    @OneToOne (mappedBy = "product")
    private Appointment appointment;


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
}
