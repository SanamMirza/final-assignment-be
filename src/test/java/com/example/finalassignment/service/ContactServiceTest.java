package com.example.finalassignment.service;

import com.example.finalassignment.dto.ContactDto;
import com.example.finalassignment.exception.RecordNotFoundException;
import com.example.finalassignment.model.Contact;
import com.example.finalassignment.repositories.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

    @Mock
    ContactRepository contactRepository;

    @InjectMocks
    ContactService contactService;

    Contact contact1;

    Contact contact2;

    ContactDto contactDto1;

    @BeforeEach
    void setUp() {
        contact1 = new Contact(1L, "test1@mail.nl", "Test1", "Tester1", "hallo");
        contact2 = new Contact(2L, "test2@mail.nl", "Test2", "Tester2", "doei");
        contactDto1 = new ContactDto(1L, "test1@mail.nl", "Test1", "Tester1", "hallo");
    }

    @Test
    void getContacts() {
        when(contactRepository.findAll()).thenReturn(List.of(contact1, contact2));

        List<Contact> contacts = contactRepository.findAll();
        List<ContactDto> dtos = contactService.getContacts();

        assertEquals(contacts.get(0).getId(), dtos.get(0).getId());
        assertEquals(contacts.get(0).getEmail(), dtos.get(0).getEmail());
        assertEquals(contacts.get(0).getFirstName(), dtos.get(0).getFirstName());
        assertEquals(contacts.get(0).getLastName(), dtos.get(0).getLastName());
        assertEquals(contacts.get(0).getMessage(), dtos.get(0).getMessage());
    }

    @Test
    void getContact() {
        Long id = 1L;
        when(contactRepository.findById(id)).thenReturn(Optional.of(contact1));
        Contact contact = contactRepository.findById(id).get();
        ContactDto dto = contactService.getContact(id);

        assertEquals(contact.getId(), dto.getId());
        assertEquals(contact.getEmail(), dto.getEmail());
        assertEquals(contact.getFirstName(), dto.getFirstName());
        assertEquals(contact.getLastName(), dto.getLastName());
        assertEquals(contact.getMessage(), dto.getMessage());
    }

    @Test
    void getContactThrowsExceptionTest() {
        assertThrows(RecordNotFoundException.class, () -> contactService.getContact(null));
    }

    @Test
    void createContact() {
        when(contactRepository.save(contact1)).thenReturn(contact1);

        contactService.createContact(contactDto1);

        assertEquals(contact1.getId(), contactDto1.getId());
        assertEquals(contact1.getEmail(), contactDto1.getEmail());
        assertEquals(contact1.getFirstName(), contactDto1.getFirstName());
        assertEquals(contact1.getLastName(), contactDto1.getLastName());
        assertEquals(contact1.getMessage(), contactDto1.getMessage());
    }

    @Test
    void deleteContactForm() {
        contactService.deleteContactForm(1L);

        verify(contactRepository).deleteById(1L);
    }
}