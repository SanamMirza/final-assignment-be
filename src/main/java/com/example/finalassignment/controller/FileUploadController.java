package com.example.finalassignment.controller;
import com.example.finalassignment.fileUploadResponse.FileUploadResponse;
import com.example.finalassignment.model.FileUpload;
import com.example.finalassignment.service.FileUploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;


@RestController
@RequestMapping("/docs")
public class FileUploadController {
    private final FileUploadService fileUploadService;

    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }


    @PostMapping("/single/upload/{username}")
    public FileUploadResponse singleFileUpload(@RequestParam("file") MultipartFile file, @PathVariable String username) throws IOException {


        // next line makes url. example "http://localhost:8080/download/naam.jpg"
        FileUpload fileUpload = fileUploadService.uploadFileDocument(file, username);
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("docs/downloadFrom/").path(Objects.requireNonNull(file.getOriginalFilename())).toUriString();

        String contentType = file.getContentType();

        return new FileUploadResponse(fileUpload.getFileName(), contentType, url);
    }

    @GetMapping("/downloadFrom/{fileName}")
    ResponseEntity<byte[]> downLoadSingleFile(@PathVariable String fileName, HttpServletRequest request) {

        return fileUploadService.singleFileDownload(fileName, request);
    }

}






