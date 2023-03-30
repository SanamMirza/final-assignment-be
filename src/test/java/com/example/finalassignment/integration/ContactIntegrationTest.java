package com.example.finalassignment.integration;

import com.example.finalassignment.dto.ContactDto;
import com.example.finalassignment.model.Contact;
import com.example.finalassignment.repositories.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class ContactIntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ContactRepository contactRepository;

    Contact contact1;
    Contact contact2;
    ContactDto contactDto1;
    ContactDto contactDto2;
    ContactDto contactDto3;

    @BeforeEach
    public void setUp() {
            if (contactRepository.count()>0){
                contactRepository.deleteAll();
            }

        contact1 = new Contact(1L, "test1@mail.nl", "Test1", "Tester1", "hoi!");

        contact2 = new Contact(2L, "test2@mail.nl", "Test2", "Tester2", "daag!");

        contact1 = contactRepository.save(contact1);
        contact2 = contactRepository.save(contact2);

        contactDto1 = new ContactDto(contact1.getId(), "test1@mail.nl", "Test1", "Tester1", "hoi!");

        contactDto2 = new ContactDto(contact2.getId(), "test2@mail.nl", "Test2", "Tester2", "daag!");

        contactDto3 = new ContactDto(3L, "test3@mail.nl", "Test3", "Tester3", "test!");


    }

    @Test
    void getContacts() throws Exception{

        mockMvc.perform(get("/contacts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(contact1.getId().toString()))
                .andExpect(jsonPath("$[0].email").value("test1@mail.nl"))
                .andExpect(jsonPath("$[0].firstName").value("Test1"))
                .andExpect(jsonPath("$[0].lastName").value("Tester1"))
                .andExpect(jsonPath("$[0].message").value("hoi!"))
                .andExpect(jsonPath("$[1].id").value(contact2.getId().toString()))
                .andExpect(jsonPath("$[1].email").value("test2@mail.nl"))
                .andExpect(jsonPath("$[1].firstName").value("Test2"))
                .andExpect(jsonPath("$[1].lastName").value("Tester2"))
                .andExpect(jsonPath("$[1].message").value("daag!"));

    }

    @Test
    void getContact() throws Exception {

        mockMvc.perform(get("/contacts/" + contact1.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(contact1.getId().toString()))
                .andExpect(jsonPath("email").value("test1@mail.nl"))
                .andExpect(jsonPath("firstName").value("Test1"))
                .andExpect(jsonPath("lastName").value("Tester1"))
                .andExpect(jsonPath("message").value("hoi!"));
    }

    @Test
    void deleteContactForm() throws Exception {

        mockMvc.perform(delete("/contacts/" + contact1.getId().toString()))
                .andExpect(status().isNoContent());
    }

}