package com.example.demo.exception;

import com.example.demo.util.Error;

public class AuthFailedException extends RuntimeException {
    private Error error;

    public AuthFailedException(Error error) {
        this.error = error;
    }

    public Error getError() {
        return error;
    }
}
