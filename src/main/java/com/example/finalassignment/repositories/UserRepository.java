package com.example.finalassignment.repositories;

import com.example.finalassignment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
