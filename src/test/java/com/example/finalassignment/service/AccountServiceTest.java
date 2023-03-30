package com.example.finalassignment.service;

import com.example.finalassignment.dto.AccountDto;
import com.example.finalassignment.exception.RecordNotFoundException;
import com.example.finalassignment.exception.UsernameNotFoundException;
import com.example.finalassignment.model.Account;
import com.example.finalassignment.model.User;
import com.example.finalassignment.repositories.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    AccountService accountService;

    @Captor
    ArgumentCaptor<Account> captor;

    Account testAccount1;
    Account testAccount2;


    @BeforeEach
    void setUp() {
        User user1 = new User();
        user1.setUsername("Will");
               testAccount1 = new Account
                (1L, "Will", "Willem", "Willemsen", "willemstraat 10", "1211WW", "willem@mail.nl", 123456789L, null, null, user1);

        User user2 = new User();
        user2.setUsername("Karel");
        testAccount2 = new Account
                (2L, "Karel", "Karel", "Karelsen", "karelstraat 10", "1111KK", "karel@mail.nl", 234567891L, null, null, user2);


    }

    @Test
    void getAccounts() {
        when(accountRepository.findAll()).thenReturn(List.of(testAccount1, testAccount2));

        List<Account> accounts = accountRepository.findAll();
        List<AccountDto> dtos = accountService.getAccounts();

        assertEquals(accounts.get(0).getId(), dtos.get(0).getId());
        assertEquals(accounts.get(0).getUsername(), dtos.get(0).getUsername());
        assertEquals(accounts.get(0).getFirstName(), dtos.get(0).getFirstName());
        assertEquals(accounts.get(0).getLastName(), dtos.get(0).getLastName());
        assertEquals(accounts.get(0).getAddress(), dtos.get(0).getAddress());
        assertEquals(accounts.get(0).getZipCode(), dtos.get(0).getZipCode());
        assertEquals(accounts.get(0).getEmail(), dtos.get(0).getEmail());
        assertEquals(accounts.get(0).getTelephoneNumber(), dtos.get(0).getTelephoneNumber());
    }

    @Test
    void getAccount() {
        Long id = 2L;
        when(accountRepository.findById(id)).thenReturn(Optional.of(testAccount2));
        Account account = accountRepository.findById(id).get();
        AccountDto dto = accountService.getAccount(id);

        assertEquals(account.getUsername(), dto.getUsername());
        assertEquals(account.getFirstName(), dto.getFirstName());
        assertEquals(account.getLastName(), dto.getLastName());
        assertEquals(account.getAddress(), dto.getAddress());
        assertEquals(account.getZipCode(), dto.getZipCode());
        assertEquals(account.getEmail(), dto.getEmail());
        assertEquals(account.getTelephoneNumber(), dto.getTelephoneNumber());
    }

    @Test
    void getAccountThrowsExceptionTest() {
        assertThrows(UsernameNotFoundException.class, () -> accountService.getAccount(null));
    }

    @Test
    void updateAccount() {
        when(accountRepository.existsById(1L)).thenReturn(true);
        when(accountRepository.findById(1L)).thenReturn(Optional.of(testAccount1));

        AccountDto accountDto = new AccountDto("Will", 1L, "Willem", "Willemsen", "unknown 10", "1211WW", "willem@mail.nl", 020232321L, null,
            null, null);

        accountService.updateAccount(1L, accountDto);

        verify(accountRepository, times(1)).findById(1L);
        verify(accountRepository, times(1)).save(captor.capture());

        Account captured = captor.getValue();

        assertEquals(accountDto.getAddress(), captured.getAddress());
        assertEquals(accountDto.getZipCode(), captured.getZipCode());
        assertEquals(accountDto.getTelephoneNumber(), captured.getTelephoneNumber());
    }

    @Test
    void updateAccountThrowsExceptionTest() {
        AccountDto accountDto = new AccountDto("Will", 1L, "Willem", "Unknown", "unknown 10", "1211WW", "willem@mail.nl", 020232321L, null,
                null, null);

        assertThrows(RecordNotFoundException.class, () -> accountService.updateAccount(null, accountDto));
    }

}