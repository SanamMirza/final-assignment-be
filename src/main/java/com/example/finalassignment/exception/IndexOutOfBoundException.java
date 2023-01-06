package com.example.finalassignment.exception;

import java.io.Serial;

public class IndexOutOfBoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public IndexOutOfBoundException() {
        super();
    }

    public IndexOutOfBoundException(String message) {
        super(message);
    }
}
