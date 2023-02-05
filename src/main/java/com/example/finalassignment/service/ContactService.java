package com.example.finalassignment.service;

import com.example.finalassignment.dto.ContactDto;
import com.example.finalassignment.exception.RecordNotFoundException;
import com.example.finalassignment.model.Contact;
import com.example.finalassignment.repositories.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<ContactDto> getContacts() {
        List<ContactDto> collection = new ArrayList<>();
        List<Contact> list = contactRepository.findAll();
        for (Contact contact : list) {
            collection.add(fromContact(contact));
        }
        return collection;
    }

    public ContactDto getContact(String email) {
        ContactDto dto = new ContactDto();
        Optional<Contact> contact = contactRepository.findById(email);
        if (contact.isPresent()) {
            dto = fromContact(contact.get());
        } else {
            throw new RecordNotFoundException(email);
        }
        return dto;
    }

    public ContactDto createContact(ContactDto contactDto) {

        Contact contact = toContact(contactDto);
        contactRepository.save(contact);

        return contactDto;
    }


    public static ContactDto fromContact(Contact contact){

        var dto = new ContactDto();

        dto.email = contact.getEmail();
        dto.firstName = contact.getFirstName();
        dto.lastName = contact.getLastName();
        dto.message = contact.getMessage();

        return dto;
    }

    public Contact toContact(ContactDto contactDto) {

        var contact = new Contact();

        contact.setEmail(contactDto.getEmail());
        contact.setFirstName(contactDto.getFirstName());
        contact.setLastName(contactDto.getLastName());
        contact.setMessage(contactDto.getMessage());

        return contact;
    }

}
