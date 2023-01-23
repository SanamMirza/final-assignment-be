package com.example.finalassignment.service;

import com.example.finalassignment.dto.FileUploadDto;
import com.example.finalassignment.model.FileUpload;
import com.example.finalassignment.repositories.FileUploadRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileUploadService {
    private final FileUploadRepository fileUploadRepository;

    public FileUploadService(FileUploadRepository fileUploadRepository) {
        this.fileUploadRepository = fileUploadRepository;
    }

    public Long createFile(FileUploadDto fileUploadDto) {
        FileUpload newFileUpload = new FileUpload();

        newFileUpload.setUploadFile((fileUploadDto.uploadFile));
        newFileUpload.setForm((fileUploadDto.form));

        return newFileUpload.getId();
    }

    public List<FileUploadDto> getFiles() {
        List<FileUpload> allFiles = fileUploadRepository.findAll();
        List<FileUploadDto> fileUploadDtoList = new ArrayList<>();
        for (FileUpload f : allFiles) {
            FileUploadDto newFileUploadDto = new FileUploadDto();
            newFileUploadDto.uploadFile = f.getUploadFile();
            newFileUploadDto.form = f.getForm();

            fileUploadDtoList.add(newFileUploadDto);
        }
        return fileUploadDtoList;

    }

    public void deleteFile (@RequestBody Long id) {
        fileUploadRepository.deleteById(id);
    }
}
