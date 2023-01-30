package com.example.finalassignment.controller;

import com.example.finalassignment.dto.AccountDto;
import com.example.finalassignment.repositories.UserRepository;
import com.example.finalassignment.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static com.example.finalassignment.utils.Utils.getErrorString;


@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;
    private final UserRepository userRepository;

    public AccountController(AccountService accountService1, UserRepository userRepository) {
        this.accountService = accountService1;
        this.userRepository = userRepository;
    }

    @PostMapping("")
    public ResponseEntity<String> createAccount(@Valid @RequestBody AccountDto accountDto, BindingResult br) {

        if (br.hasErrors()) {
            String errorString = getErrorString(br);
            return new ResponseEntity<>(errorString, HttpStatus.BAD_REQUEST);
        } else {
            Long createdId = accountService.createAccount(accountDto);
            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/accounts/" + createdId)
                    .toUriString());
            return ResponseEntity.created(uri).body("Account created");
        }
    }

    @GetMapping("")
    public ResponseEntity<List<AccountDto>> getAccounts() {
        return ResponseEntity.ok(accountService.getAccounts());
    }

    @GetMapping("/{username}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable ("username") Long id) {
        AccountDto optionalAccount = accountService.getAccount(id);

        return ResponseEntity.ok().body(optionalAccount);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<AccountDto> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/user/{userId}")
    public void assignUserToAccount(@PathVariable Long id, @PathVariable String userId) {
        accountService.assignUserToAccount(id, userId) ;
    }

    @PutMapping("/{id}")
    public AccountDto updateUser(@PathVariable("id") Long id, @RequestBody AccountDto dto) {
        accountService.updateAccount(id, dto);
        return dto;
    }
}





