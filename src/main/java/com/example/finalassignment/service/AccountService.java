package com.example.finalassignment.service;

import com.example.finalassignment.dto.AccountDto;
import com.example.finalassignment.exception.RecordNotFoundException;
import com.example.finalassignment.exception.UsernameNotFoundException;
import com.example.finalassignment.model.Account;
import com.example.finalassignment.model.User;
import com.example.finalassignment.repositories.AccountRepository;

import com.example.finalassignment.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public AccountService(AccountRepository accountRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Long createAccount (AccountDto accountDto) {
        Account newAccount = new Account();

        newAccount.setFirstName(accountDto.getFirstName());
        newAccount.setLastName(accountDto.getLastName());
        newAccount.setAddress(accountDto.getAddress());
        newAccount.setTelephoneNumber(accountDto.getTelephoneNumber());
        newAccount.setEmailAddress(accountDto.getEmailAddress());

        return accountRepository.save(newAccount).getId();

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
        AccountDto dto = new AccountDto();
        Optional<Account> account = accountRepository.findById(id);

        if (account.isPresent()) {
            dto = fromAccount(account.get());
        } else {
            throw new UsernameNotFoundException("user not found");
        }
        return dto;
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

    public void assignUserToAccount(Long id, String userId) {
        var optionalAccount = accountRepository.findById(id);
        var optionalUser = userRepository.findById(userId);

        if (optionalAccount.isPresent() && optionalUser.isPresent()) {
            var account = optionalAccount.get();
            var user = optionalUser.get();

            account.setUser(user);
            accountRepository.save(account);
            user.setAccount(account);
            userRepository.save(user);
        } else {
            throw new RecordNotFoundException("Record not found!");
        }
    }

    public AccountDto addAccount(AccountDto accountDto) {
        Account account =  toAccount(accountDto);
        accountRepository.save(account);
        return accountDto;
    }

    public static AccountDto fromAccount(Account account){
        var dto = new AccountDto();

        dto.firstName = account.getFirstName();
        dto.lastName = account.getLastName();
        dto.address = account.getAddress();
        dto.emailAddress = account.getEmailAddress();
        dto.telephoneNumber = account.getTelephoneNumber();

        if(account.getUser()!=null) {
            dto.setUsername(account.getUser().getUsername());
        }

        return dto;
    }

    public static Account toAccount(AccountDto accountDto){
        var account = new Account();

        account.setFirstName(accountDto.firstName);
        account.setLastName(accountDto.lastName);
        account.setAddress(accountDto.address);
        account.setEmailAddress(accountDto.emailAddress);
        account.setTelephoneNumber(accountDto.telephoneNumber);
        account.setUsername(accountDto.username);

        return account;
    }





}