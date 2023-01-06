package com.example.finalassignment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class FileUpload {
    @Id
    @GeneratedValue
    private Long id;
    private byte uploadFile;
    private byte form;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(byte uploadFile) {
        this.uploadFile = uploadFile;
    }

    public byte getForm() {
        return form;
    }

    public void setForm(byte form) {
        this.form = form;
    }
}
