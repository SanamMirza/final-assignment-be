package com.example.finalassignment.repositories;

import com.example.finalassignment.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByEmail(String email);


}
