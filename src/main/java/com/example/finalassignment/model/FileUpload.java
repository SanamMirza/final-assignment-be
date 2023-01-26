package com.example.finalassignment.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class FileUpload {
    @Id
    @GeneratedValue
    private Long id;

    private String fileName;
    @Lob
    private byte[] uploadFile;
    @NotBlank
    private byte form;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(byte[] uploadFile) {
        this.uploadFile = uploadFile;
    }

    public byte getForm() {
        return form;
    }

    public void setForm(byte form) {
        this.form = form;
    }
}
