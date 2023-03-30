package com.example.finalassignment.service;

import com.example.finalassignment.exception.UsernameNotFoundException;
import com.example.finalassignment.model.Account;
import com.example.finalassignment.model.FileUpload;
import com.example.finalassignment.model.User;
import com.example.finalassignment.repositories.FileUploadRepository;
import com.example.finalassignment.repositories.UserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
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

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + fileUpload.getFileName()).body(fileUpload.getUploadFile());

    }



}

