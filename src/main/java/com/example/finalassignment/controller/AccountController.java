package com.example.finalassignment.controller;

import com.example.finalassignment.dto.AccountDto;
import com.example.finalassignment.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;



@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping("")
    public ResponseEntity<List<AccountDto>> getAccounts() {
        return ResponseEntity.ok(accountService.getAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable ("id") Long id) {
        AccountDto optionalAccount = accountService.getAccount(id);

        return ResponseEntity.ok().body(optionalAccount);
    }


    @PutMapping("/{id}")
    public AccountDto updateAccount(@PathVariable("id") Long id,  @RequestBody AccountDto dto) {
        accountService.updateAccount(id, dto);
        return dto;
     }



}





