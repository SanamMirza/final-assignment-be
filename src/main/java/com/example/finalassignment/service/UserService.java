package com.example.finalassignment.service;
import com.example.finalassignment.dto.AccountDto;
import com.example.finalassignment.dto.AccountUserDto;
import com.example.finalassignment.dto.UserDto;
import com.example.finalassignment.exception.RecordNotFoundException;
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



    public UserService (PasswordEncoder passwordEncoder, UserRepository userRepository, AccountRepository accountRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }
    public List<UserDto> getUsers() {
        List<UserDto> collection = new ArrayList<>();
        List<User> list = userRepository.findAll();
        for (User user : list) {
            collection.add(fromUser(user));
        }
        return collection;
    }

    public UserDto getUser(String username) {
        UserDto dto = new UserDto();
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()) {
            dto = fromUser(user.get());
        } else {
            throw new UsernameNotFoundException(username);
        }
        return dto;
    }


    public boolean userExists(String username) {
        return userRepository.existsById(username);
    }

    public String createUser(AccountUserDto accountUserDto) {
        Account account = new Account();
        User user = new User();

        //Alle account properties vullen
        account.setFirstName(accountUserDto.getFirstName());
        account.setLastName(accountUserDto.getLastName());
        account.setAddress(accountUserDto.getAddress());
        account.setTelephoneNumber(accountUserDto.getTelephoneNumber());
        account.setEmail(accountUserDto.getEmail());
        account.setUsername(accountUserDto.getUsername());

        //Zelfde voor user (username en password)
        user.setUsername(accountUserDto.getUsername());
        user.setPassword(passwordEncoder.encode(accountUserDto.getPassword()));
        user.setEmail(accountUserDto.getEmail());
        //Account savedAccount = accountRespository.save(account)
        Account savedAccount = accountRepository.save(account);
        // user.setAccount(savedAccount)
        user.setAccount(savedAccount);

        //userRepository.save(user);
        User newUser = userRepository.save(user);

        return newUser.getUsername();

    }




    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    public void updateUser(String username, UserDto newUser) {
        if (!userRepository.existsById(username)) throw new RecordNotFoundException("Record not found");
        User user = userRepository.findById(username).get();
        user.setPassword(newUser.getPassword());
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

    public UserDto addUser(UserDto userDto) {
        User user =  toUser(userDto);
        userRepository.save(user);
        return userDto;
    }



    public static UserDto fromUser(User user){

        var dto = new UserDto();

        dto.username = user.getUsername();
        dto.password = user.getPassword();
        dto.email = user.getEmail();
        dto.authorities = user.getAuthorities();
        if(user.getAccount() != null) {
            dto.setAccountDto(AccountService.fromAccount(user.getAccount()));
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
