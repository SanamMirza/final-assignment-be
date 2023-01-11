package com.example.finalassignment.service;

import com.example.finalassignment.dto.UserDto;
import com.example.finalassignment.model.User;
import com.example.finalassignment.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Long createUser(UserDto userDto) {
        User newUser = new User();

        newUser.setFirstName((userDto.firstName));
        newUser.setLastName((userDto.lastName));
        newUser.setAddress((userDto.address));
        newUser.setEmailAddress((userDto.emailAddress));
        newUser.setTelephoneNumber((userDto.telephoneNumber));

        return newUser.getId();

    }

    public List<UserDto> getUsers() {
        List<User> allUsers = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for (User u : allUsers) {
            UserDto newUserDto = new UserDto();
            newUserDto.firstName = u.getFirstName();
            newUserDto.lastName = u.getLastName();
            newUserDto.address = u.getAddress();
            newUserDto.emailAddress = u.getEmailAddress();
            newUserDto.telephoneNumber = u.getTelephoneNumber();
            userDtoList.add(newUserDto);
    }
        return userDtoList;

    }

    public void deleteUser (@RequestBody Long id) {
        userRepository.deleteById(id);
    }


}