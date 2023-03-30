package com.example.finalassignment.exception;

public class EmailAlreadyExist extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EmailAlreadyExist(String username) {
        super("This email address already exist, please try again");
    }

}

