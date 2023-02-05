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



@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

//    @PostMapping("")
//    public ResponseEntity<String> createAccount(@Valid @RequestBody AccountDto accountDto, BindingResult br) {
//
//        if (br.hasErrors()) {
//            String errorString = getErrorString(br);
//            return new ResponseEntity<>(errorString, HttpStatus.BAD_REQUEST);
//        } else {
//            Long createdId = accountService.createAccount(accountDto);
//            URI uri = URI.create(ServletUriComponentsBuilder
//                    .fromCurrentContextPath()
//                    .path("/accounts/" + createdId)
//                    .toUriString());
//            return ResponseEntity.created(uri).body("Account created");
//        }
//    }

    @GetMapping("")
    public ResponseEntity<List<AccountDto>> getAccounts() {
        return ResponseEntity.ok(accountService.getAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable ("id") Long id) {
        AccountDto optionalAccount = accountService.getAccount(id);

        return ResponseEntity.ok().body(optionalAccount);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<AccountDto> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/user/{username}")
    public void assignUserToAccount(@PathVariable Long id, @PathVariable String username) {
        accountService.assignUserToAccount(id, username) ;
    }

    @PutMapping("/{id}")
    public AccountDto updateAccount(@PathVariable("id") Long id,  @RequestBody AccountDto dto) {
        accountService.updateAccount(id, dto);
        return dto;
     }


}





