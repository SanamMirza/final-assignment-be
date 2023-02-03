package com.example.finalassignment.controller;
import com.example.finalassignment.fileUploadResponse.FileUploadResponse;
import com.example.finalassignment.model.FileUpload;
import com.example.finalassignment.service.FileUploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/docs")
public class FileUploadController {
    private final FileUploadService fileUploadService;

    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }


    @PostMapping("single/upload")
    public FileUploadResponse singleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {


        // next line makes url. example "http://localhost:8080/download/naam.jpg"
        FileUpload fileUpload = fileUploadService.uploadFileDocument(file);
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFrom/").path(Objects.requireNonNull(file.getOriginalFilename())).toUriString();

        String contentType = file.getContentType();

        return new FileUploadResponse(fileUpload.getFileName(), url, contentType );
    }

    //    get for single download
    @GetMapping("/download/{fileName}")
    ResponseEntity<byte[]> downLoadSingleFile(@PathVariable String fileName, HttpServletRequest request) {

        return fileUploadService.singleFileDownload(fileName, request);
    }

    //    post for multiple uploads to database
    @PostMapping("/multiple/upload")
    List<FileUploadResponse> multipleUpload(@RequestParam("files") MultipartFile [] files) {

        if(files.length > 7) {
            throw new RuntimeException("to many files selected");
        }

        return fileUploadService.createMultipleUpload(files);

    }

    @GetMapping("zipDownload")
    public void zipDownload(@RequestBody String[] files, HttpServletResponse response) throws IOException {

        fileUploadService.getZipDownload(files, response);

    }

    @GetMapping("/getAll/db")
    public Collection<FileUpload> getAllFromDB(){
        return fileUploadService.getALlFromDB();
    }
}



