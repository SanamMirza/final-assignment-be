package com.example.finalassignment.service;
import com.example.finalassignment.dto.AccountUserDto;
import com.example.finalassignment.dto.UserDto;
import com.example.finalassignment.exception.BadRequestException;
import com.example.finalassignment.exception.EmailAlreadyExist;
import com.example.finalassignment.exception.UsernameNotFoundException;
import com.example.finalassignment.model.Account;
import com.example.finalassignment.model.Authority;
import com.example.finalassignment.model.User;
import com.example.finalassignment.repositories.AccountRepository;
import com.example.finalassignment.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final AccountService accountService;


    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, AccountRepository accountRepository, AccountService accountService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.accountService = accountService;
    }

    public List<UserDto> getUsers() {
        List<User> list = userRepository.findAll();
        List<UserDto> collection = new ArrayList<>();
        for (User user : list) {
            collection.add(fromUser(user));
        }
        return collection;
    }

    public UserDto getUser(String username) {
        UserDto dto;
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()) {
            dto = fromUser(user.get());
        } else {
            throw new UsernameNotFoundException(username);
        }
        return dto;
    }


    public String createUser(AccountUserDto accountUserDto) {
        if (userRepository.findByUsername(accountUserDto.getUsername()) != null) {
            throw new BadRequestException(accountUserDto.getUsername() + " already exist, please choose another username.");
        }
        Account account = new Account();
        User user = new User();

            account.setFirstName(accountUserDto.getFirstName());
            account.setLastName(accountUserDto.getLastName());
            account.setAddress(accountUserDto.getAddress());
            account.setZipCode(accountUserDto.getZipCode());
            account.setTelephoneNumber(accountUserDto.getTelephoneNumber());
            account.setEmail(accountUserDto.getEmail());
            account.setUsername(accountUserDto.getUsername());

            user.setUsername(accountUserDto.getUsername());
            user.setPassword(passwordEncoder.encode(accountUserDto.getPassword()));
            user.setEmail(accountUserDto.getEmail());

            try {
                Account savedAccount = accountRepository.save(account);
                user.setAccount(savedAccount);

                User newUser = userRepository.save(user);
                return newUser.getUsername();


            } catch (Exception exception) {
                throw new EmailAlreadyExist(accountUserDto.getEmail());

            }

        }


    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    public void updateUser(String username, UserDto newUser) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(user);
    }
    

    public Set<Authority> getAuthorities(String username) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        UserDto userDto = fromUser(user);
        return userDto.getAuthorities();
    }

    public void addAuthority(String username, String authority) {

        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.addAuthority(new Authority(username, authority));
        userRepository.save(user);
    }

    public void removeAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        userRepository.save(user);
    }



    public UserDto fromUser(User user){

        var dto = new UserDto();

        dto.username = user.getUsername();
        dto.password = user.getPassword();
        dto.email = user.getEmail();
        dto.authorities = user.getAuthorities();
        if(user.getAccount() != null) {
            dto.setAccountDto(accountService.fromAccount(user.getAccount()));
        }

        return dto;
    }

    public User toUser(UserDto userDto) {

        var user = new User();

        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());

        return user;
    }


}
