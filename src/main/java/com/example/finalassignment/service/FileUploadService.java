package com.example.finalassignment.service;

import com.example.finalassignment.exception.UsernameNotFoundException;
import com.example.finalassignment.model.Account;
import com.example.finalassignment.model.FileUpload;
import com.example.finalassignment.model.User;
import com.example.finalassignment.repositories.FileUploadRepository;
import com.example.finalassignment.repositories.UserRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;

@Service
public class FileUploadService {
    private final FileUploadRepository fileUploadRepository;
    private final UserRepository userRepository;

    public FileUploadService(FileUploadRepository fileUploadRepository,
                             UserRepository userRepository) {
        this.fileUploadRepository = fileUploadRepository;
        this.userRepository = userRepository;
    }

    public Collection<FileUpload> getALlFromDB() {
        Collection<FileUpload> fromDatabase = fileUploadRepository.findAll();
        return fromDatabase;


    }

    public FileUpload uploadFileDocument(MultipartFile file, String username) throws IOException {
        String name = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        User user = userRepository.findById(username).orElseThrow(()-> new UsernameNotFoundException("no user found with id " + username));
        Account account = user.getAccount();
        FileUpload fileUpload = new FileUpload();

        fileUpload.setFileName(name);
        fileUpload.setUploadFile(file.getBytes());
        fileUpload.setAccount(account);

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

