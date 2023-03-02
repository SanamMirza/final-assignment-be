package com.example.finalassignment.dto;
import com.example.finalassignment.model.Product;
import javax.validation.constraints.NotNull;

public class AppointmentDto {
    @NotNull
    public String appointmentDate;
    public String appointmentTime;

    public Product product;




   public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

