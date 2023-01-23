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
    private final AccountService accountervice;

    public AccountController(AccountService accountService) {
        accountervice = accountService;
    }

    @PostMapping("")
    public ResponseEntity<String> createAccount(@Valid @RequestBody AccountDto accountDto, BindingResult br) {

        if (br.hasErrors()) {
            String errorString = getErrorString(br);
            return new ResponseEntity<>(errorString, HttpStatus.BAD_REQUEST);
        } else {
            Long createdId = accountervice.createAccount(accountDto);
            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/accounts/" + createdId)
                    .toUriString());
            return ResponseEntity.created(uri).body("Account created");
        }
    }

    @GetMapping("")
    public ResponseEntity<List<AccountDto>> getAccounts() {
        return ResponseEntity.ok(accountervice.getAccounts());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AccountDto> deleteAccount(@PathVariable Long id) {
        accountervice.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

//    @PutMapping("")
//    public ResponseEntity<AccountDto> updateAccount(@PathVariable("lastname") String lastName, @RequestBody AccountDto accountdto) {
//        accountervice.updateAccount(lastName, accountdto);
//        return ResponseEntity.noContent().build();
//
//    }
}





