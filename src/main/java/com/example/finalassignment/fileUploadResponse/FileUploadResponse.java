package com.example.finalassignment.fileUploadResponse;

public class FileUploadResponse {

    String fileName;
    String contentType;
    String url;


    public FileUploadResponse(String fileName, String contentType, String url) {
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

