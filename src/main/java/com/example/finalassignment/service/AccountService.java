package com.example.finalassignment.service;

import com.example.finalassignment.dto.AccountDto;
import com.example.finalassignment.exception.RecordNotFoundException;
import com.example.finalassignment.model.Account;
import com.example.finalassignment.repositories.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.List;


@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public Long createAccount(AccountDto accountDto) {
        Account newAccount = new Account();

        newAccount.setFirstName((accountDto.firstName));
        newAccount.setLastName((accountDto.lastName));
        newAccount.setAddress((accountDto.address));
        newAccount.setEmailAddress((accountDto.emailAddress));
        newAccount.setTelephoneNumber((accountDto.telephoneNumber));

        return newAccount.getId();

    }

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

    public void deleteAccount (@RequestBody Long id) {
        accountRepository.deleteById(id);
    }

    public void updateAccount(Long id, AccountDto newAccount) {
        if (!accountRepository.existsById(id)) throw new RecordNotFoundException("Record not found");
        Account account = accountRepository.findById(id).get();
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




}