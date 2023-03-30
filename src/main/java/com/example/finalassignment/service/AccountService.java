package com.example.finalassignment.service;

import com.example.finalassignment.dto.AccountDto;
import com.example.finalassignment.exception.RecordNotFoundException;
import com.example.finalassignment.exception.UsernameNotFoundException;
import com.example.finalassignment.model.Account;
import com.example.finalassignment.model.Authority;
import com.example.finalassignment.repositories.AccountRepository;
import com.example.finalassignment.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;


@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;


    public AccountService(AccountRepository accountRepository,
                          UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }


    public List<AccountDto> getAccounts() {
        List<Account> allAccounts = accountRepository.findAll();
        List<AccountDto> accountDtoList = new ArrayList<>();
        for (Account account : allAccounts) {
            AccountDto newAccountDto = fromAccount(account);

            accountDtoList.add(newAccountDto);
    }
        return accountDtoList;

    }

    public AccountDto getAccount(Long id) {
        AccountDto dto;
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            dto = fromAccount(account.get());
        } else {
            throw new UsernameNotFoundException("user not found");
        }
        return dto;
    }


    public void updateAccount(Long id, AccountDto newAccount) {
        if (!accountRepository.existsById(id)) throw new RecordNotFoundException("Record not found");
        Account account = accountRepository.findById(id).get();
        account.setAddress(newAccount.getAddress());
        account.setZipCode(newAccount.getZipCode());
        account.setTelephoneNumber(newAccount.getTelephoneNumber());
        accountRepository.save(account);
    }



    public static AccountDto fromAccount(Account account){
        var dto = new AccountDto();

        dto.username = account.getUsername();
        dto.firstName = account.getFirstName();
        dto.lastName = account.getLastName();
        dto.address = account.getAddress();
        dto.zipCode = account.getZipCode();
        dto.email = account.getEmail();
        dto.telephoneNumber = account.getTelephoneNumber();
        dto.appointments =account.getAppointment();
        dto.fileUploads = account.getFileUpload();
        dto.id = account.getId();
        Set<Authority> authorities = account.getUser().getAuthorities();
        List<String> strings = new ArrayList<>();
        for (Authority authority : authorities ) {
            strings.add(authority.getAuthority());
        }
        dto.authorities = strings;

        if(account.getUser()!=null) {
            dto.setUsername(account.getUser().getUsername());
        }

        return dto;
    }

    public static Account toAccount(AccountDto accountDto){
        var account = new Account();

        account.setUsername(accountDto.getUsername());
        account.setFirstName(accountDto.getFirstName());
        account.setLastName(accountDto.getLastName());
        account.setAddress(accountDto.getAddress());
        account.setZipCode(accountDto.getZipCode());
        account.setEmail(accountDto.getEmail());
        account.setTelephoneNumber(accountDto.getTelephoneNumber());
        account.setUsername(accountDto.getUsername());
        account.setId(accountDto.getId());

        return account;
    }

}