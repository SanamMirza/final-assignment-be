package com.example.finalassignment.controller;

import com.example.finalassignment.dto.AccountDto;
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

    public AccountController(AccountService accountService1) {
        this.accountService = accountService1;
    }

//    @PostMapping("")
//    public ResponseEntity<String> createAccount(@Valid @RequestBody AccountDto accountDto, BindingResult br) {
//
//        if (br.hasErrors()) {
//            String errorString = getErrorString(br);
//            return new ResponseEntity<>(errorString, HttpStatus.BAD_REQUEST);
//        } else {
//            String newUsername = accountService.createAccount(accountDto);
//            URI uri = URI.create(ServletUriComponentsBuilder
//                    .fromCurrentContextPath()
//                    .path("/accounts/" + newUsername)
//                    .toUriString());
//            return ResponseEntity.created(uri).body("Account created");
//        }
//    }

    @GetMapping("")
    public ResponseEntity<List<AccountDto>> getAccounts() {
        return ResponseEntity.ok(accountService.getAccounts());
    }

    @GetMapping("/{username}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable ("username") String username) {
        AccountDto optionalAccount = accountService.getAccount(username);

        return ResponseEntity.ok().body(optionalAccount);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<AccountDto> deleteAccount(@PathVariable String username) {
        accountService.deleteAccount(username);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/users")
    public void assignUserToAccount(@PathVariable ("id") String id, @Valid @RequestBody AccountDto accountDto) {
        accountService.assignUserToAccount(id, accountDto.username);
    }
}





