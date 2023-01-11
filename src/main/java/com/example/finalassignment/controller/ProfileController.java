package com.example.finalassignment.controller;

import com.example.finalassignment.dto.UserDto;
import com.example.finalassignment.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static com.example.finalassignment.utils.Utils.getErrorString;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService Userservice;

    public UserController(UserService userService, UserService userservice) {
        Userservice = userservice;
    }

    @PostMapping("")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDto userDto, BindingResult br) {

        if (br.hasErrors()) {
            String errorString = getErrorString(br);
            return new ResponseEntity<>(errorString, HttpStatus.BAD_REQUEST);
        } else {
            Long createdId = Userservice.createUser(userDto);
            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/users/" + createdId)
                    .toUriString());
            return ResponseEntity.created(uri).body("User created");
        }
    }

    @GetMapping("")
    public ResponseEntity<Iterable<UserDto>> getUsers() {
        return ResponseEntity.ok(Userservice.getUsers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Long id) {
        Userservice.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}





