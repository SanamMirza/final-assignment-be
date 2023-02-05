package com.example.finalassignment.controller;

import com.example.finalassignment.dto.ContactDto;
import com.example.finalassignment.service.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }


    @GetMapping("")
    public ResponseEntity<List<ContactDto>> getContacts() {
        return ResponseEntity.ok(contactService.getContacts());

    }


    @GetMapping("/{id}")
    public ResponseEntity<ContactDto> getContact(@PathVariable("email") String email) {
        ContactDto optionalContact = contactService.getContact(email);

        return ResponseEntity.ok().body(optionalContact);
    }

    @PostMapping("")
    public ContactDto createContact(@RequestBody ContactDto contactDto) {
        ContactDto dto = contactService.createContact(contactDto);

        return dto;

    }

}
