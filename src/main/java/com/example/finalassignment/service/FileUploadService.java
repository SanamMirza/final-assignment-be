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

        String mimeType = request.getServletContext().getMimeType(fileUpload.getFileName());


        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + fileUpload.getFileName()).body(fileUpload.getUploadFile());

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


}

