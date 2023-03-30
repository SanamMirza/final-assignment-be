package com.example.finalassignment.repositories;

import com.example.finalassignment.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
