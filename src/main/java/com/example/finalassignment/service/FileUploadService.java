package com.example.finalassignment.service;

import com.example.finalassignment.fileUploadResponse.FileUploadResponse;
import com.example.finalassignment.model.FileUpload;
import com.example.finalassignment.repositories.FileUploadRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class FileUploadService {
    private final FileUploadRepository fileUploadRepository;

    public FileUploadService(FileUploadRepository fileUploadRepository) {
        this.fileUploadRepository = fileUploadRepository;
    }

    public Collection<FileUpload> getALlFromDB() {
        return fileUploadRepository.findAll();
    }

    public FileUpload uploadFileDocument(MultipartFile file) throws IOException {
        String name = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        FileUpload fileUpload = new FileUpload();
        fileUpload.setFileName(name);
        fileUpload.setUploadFile(file.getBytes());

        fileUploadRepository.save(fileUpload);

        return fileUpload;

    }
    @Transactional
    public ResponseEntity<byte[]> singleFileDownload(String fileName, HttpServletRequest request){

        FileUpload fileUpload = fileUploadRepository.findByFileName(fileName);

//        this mediaType decides witch type you accept if you only accept 1 type
//        MediaType contentType = MediaType.IMAGE_JPEG;
//        this is going to accept multiple types

        String mimeType = request.getServletContext().getMimeType(fileUpload.getFileName());

//        for download attachment use next line
//        return ResponseEntity.ok().contentType(contentType).header(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=" + resource.getFilename()).body(resource);
//        for showing image in browser
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + fileUpload.getFileName()).body(fileUpload.getUploadFile());

    }

    public List<FileUploadResponse> createMultipleUpload(MultipartFile[] files){
        List<FileUploadResponse> uploadResponseList = new ArrayList<>();
        Arrays.stream(files).forEach(file -> {

            String name = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            FileUpload fileUpload = new FileUpload();
            fileUpload.setFileName(name);
            try {
                fileUpload.setUploadFile(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

            fileUploadRepository.save(fileUpload);

//            next line makes url. example "http://localhost:8080/download/naam.jpg"
            String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFromDB/").path(name).toUriString();

            String contentType = file.getContentType();

            FileUploadResponse response = new FileUploadResponse(name, contentType, url);

            uploadResponseList.add(response);
        });
        return uploadResponseList;
    }

    public void getZipDownload(String[] files, HttpServletResponse response) throws IOException {
        try (ZipOutputStream zos = new ZipOutputStream(response.getOutputStream())) {
            Arrays.stream(files).forEach(file -> {
                try {
                    createZipEntry(file, zos);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            zos.finish();

            response.setStatus(200);
            response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=zipfile");
        }
    }

    public Resource downLoadFileDatabase(String fileName) {

        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFromDB/").path(fileName).toUriString();

        Resource resource;

        try {
            resource = new UrlResource(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Issue in reading the file", e);
        }

        if(resource.exists()&& resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("the file doesn't exist or not readable");
        }
    }

    public void createZipEntry(String file, ZipOutputStream zos) throws IOException {

        Resource resource = downLoadFileDatabase(file);
        ZipEntry zipEntry = new ZipEntry(Objects.requireNonNull(resource.getFilename()));
        try {
            zipEntry.setSize(resource.contentLength());
            zos.putNextEntry(zipEntry);

            StreamUtils.copy(resource.getInputStream(), zos);

            zos.closeEntry();
        } catch (IOException e) {
            System.out.println("some exception while zipping");
        }

    }
}

