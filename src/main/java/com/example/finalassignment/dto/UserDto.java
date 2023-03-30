package com.example.finalassignment.dto;

import com.example.finalassignment.model.Authority;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.util.Set;

public class UserDto {

    @Column(unique = true)
    public String username;

    @NotBlank
    public String password;

    public String email;
    @JsonSerialize
    public Set<Authority> authorities;
    public AccountDto accountDto;


    public AccountDto getAccountDto() {
        return accountDto;
    }

    public void setAccountDto(AccountDto accountDto) {
        this.accountDto = accountDto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}
