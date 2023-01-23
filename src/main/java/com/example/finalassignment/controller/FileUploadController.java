package com.example.finalassignment.controller;

import com.example.finalassignment.dto.FileUploadDto;
import com.example.finalassignment.service.FileUploadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static com.example.finalassignment.utils.Utils.getErrorString;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/fileuploads")
public class FileUploadController {
    private final FileUploadService Fileuploadservice;

    public FileUploadController(FileUploadService fileuploadservice) {
        Fileuploadservice = fileuploadservice;
    }

    @PostMapping("")
    public ResponseEntity<Object> createFile(@Valid @RequestBody FileUploadDto fileUploadDto, BindingResult br) {

        if (br.hasErrors()) {
            String errorString = getErrorString(br);
            return new ResponseEntity<>(errorString, HttpStatus.BAD_REQUEST);
        } else {
            Long createdId = Fileuploadservice.createFile(fileUploadDto);
            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/fileuploads/" + createdId)
                    .toUriString());
            return ResponseEntity.created(uri).body("File created");
        }
    }

        @GetMapping("")
        public ResponseEntity<List<FileUploadDto>> getFiles() {
            return ResponseEntity.ok(Fileuploadservice.getFiles());
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<FileUploadDto> deleteFileUpload(@PathVariable Long id) {
            Fileuploadservice.deleteFile(id);
            return ResponseEntity.noContent().build();
        }

}
