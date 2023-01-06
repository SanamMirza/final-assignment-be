package com.example.finalassignment.service;

import com.example.finalassignment.dto.UserDto;
import com.example.finalassignment.model.User;
import com.example.finalassignment.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repos;

    public UserService(UserRepository repos) {
        this.repos = repos;
    }

    public Long createUser(UserDto userDto) {
        User newUser = new User();

        newUser.setFirstName(())
    }
}
