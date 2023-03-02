package com.example.finalassignment.repositories;

import com.example.finalassignment.model.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {
    FileUpload findByFileName(String fileName);
    List<FileUpload> findAllByAccountId(Long id);
}
