package com.example.finalassignment.controller;

import com.example.finalassignment.dto.ContactDto;
import com.example.finalassignment.service.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
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
    public ResponseEntity<ContactDto> getContact(@PathVariable("id") Long id) {
        ContactDto optionalContact = contactService.getContact(id);

        return ResponseEntity.ok().body(optionalContact);
    }

    @PostMapping("")
    public ContactDto createContact(@RequestBody ContactDto contactDto) {
        ContactDto dto = contactService.createContact(contactDto);

        return dto;

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ContactDto> deleteContactForm(@PathVariable Long id) {
        contactService.deleteContactForm(id);
        return ResponseEntity.noContent().build();
    }

}
