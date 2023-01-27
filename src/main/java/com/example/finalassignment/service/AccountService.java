package com.example.finalassignment.service;

import com.example.finalassignment.dto.AccountDto;
import com.example.finalassignment.exception.RecordNotFoundException;
import com.example.finalassignment.exception.UsernameNotFoundException;
import com.example.finalassignment.model.Account;
import com.example.finalassignment.repositories.AccountRepository;
import com.example.finalassignment.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;


    public AccountService(AccountRepository accountRepository,
                          UserRepository userRepository, UserService userService) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }


//    public String createAccount(AccountDto accountDto) {
//        Account newAccount = new Account();
//
//        newAccount.setFirstName((accountDto.firstName));
//        newAccount.setLastName((accountDto.lastName));
//        newAccount.setAddress((accountDto.address));
//        newAccount.setEmailAddress((accountDto.emailAddress));
//        newAccount.setTelephoneNumber((accountDto.telephoneNumber));
//
//        return newAccount.getUsername();
//
//    }

    public List<AccountDto> getAccounts() {
        List<Account> allAccounts = accountRepository.findAll();
        List<AccountDto> accountDtoList = new ArrayList<>();
        for (Account account : allAccounts) {
            AccountDto newAccountDto = new AccountDto();
            newAccountDto.firstName = account.getFirstName();
            newAccountDto.lastName = account.getLastName();
            newAccountDto.address = account.getAddress();
            newAccountDto.emailAddress = account.getEmailAddress();
            newAccountDto.telephoneNumber = account.getTelephoneNumber();
            accountDtoList.add(newAccountDto);
    }
        return accountDtoList;

    }

    public AccountDto getAccount(String username) {
        AccountDto dto = new AccountDto();
        Optional<Account> account = accountRepository.findById(username);

        if (account.isPresent()) {
            dto = fromAccount(account.get());
        } else {
            throw new UsernameNotFoundException(username);
        }
        return dto;
    }

    public void deleteAccount (@RequestBody String username) {
        accountRepository.deleteById(username);
    }

    public void updateAccount(String username, AccountDto newAccount) {
        if (!accountRepository.existsById(username)) throw new RecordNotFoundException("Record not found");
        Account account = accountRepository.findById(username).get();
        account.setTelephoneNumber(newAccount.telephoneNumber);
        accountRepository.save(account);
    }

    public static AccountDto fromAccount(Account account){
        var dto = new AccountDto();

        dto.firstName = account.getFirstName();
        dto.lastName = account.getLastName();
        dto.address = account.getAddress();
        dto.emailAddress = account.getEmailAddress();
        dto.telephoneNumber = account.getTelephoneNumber();

        return dto;
    }

    public static Account toAccount(AccountDto accountDto){
        var account = new Account();

        account.setFirstName(accountDto.firstName);
        account.setLastName(accountDto.lastName);
        account.setAddress(accountDto.address);
        account.setEmailAddress(accountDto.emailAddress);
        account.setTelephoneNumber(accountDto.telephoneNumber);

        return account;
    }

    public void assignUserToAccount(String username, String userId) {
        var optionalAccount = accountRepository.findById(username);
        var optionalUser = userRepository.findById(userId);

        if (optionalAccount.isPresent() && optionalUser.isPresent()) {
            var account = optionalAccount.get();
            var user = optionalUser.get();

            account.setUser(user);
            accountRepository.save(account);
        } else {
            throw new RecordNotFoundException("Record not found!");
        }
    }





}