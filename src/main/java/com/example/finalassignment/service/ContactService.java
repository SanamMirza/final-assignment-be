package com.example.finalassignment.service;

import com.example.finalassignment.dto.ContactDto;
import com.example.finalassignment.exception.RecordNotFoundException;
import com.example.finalassignment.model.Contact;
import com.example.finalassignment.repositories.ContactRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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

    public ContactDto getContact(Long id) {
        ContactDto dto;
        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isPresent()) {
            dto = fromContact(contact.get());
        } else {
            throw new RecordNotFoundException("contact form not found");
        }
        return dto;
    }

    public ContactDto createContact(ContactDto contactDto) {

        Contact newContact = toContact(contactDto);
        Contact savedContact = contactRepository.save(newContact);

        return fromContact(savedContact);
    }

    public void deleteContactForm (@RequestBody Long id) {
        contactRepository.deleteById(id);
    }


    public static ContactDto fromContact(Contact contact){

        var dto = new ContactDto();

        dto.id = contact.getId();
        dto.email = contact.getEmail();
        dto.firstName = contact.getFirstName();
        dto.lastName = contact.getLastName();
        dto.message = contact.getMessage();

        return dto;
    }

    public Contact toContact(ContactDto contactDto) {

        var contact = new Contact();

        contact.setId(contactDto.getId());
        contact.setEmail(contactDto.getEmail());
        contact.setFirstName(contactDto.getFirstName());
        contact.setLastName(contactDto.getLastName());
        contact.setMessage(contactDto.getMessage());

        return contact;
    }

}
