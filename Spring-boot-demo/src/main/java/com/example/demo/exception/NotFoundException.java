package com.example.demo.exception;

import com.example.demo.util.Error;

public class NotFoundException extends RuntimeException {
    private Error error;

    public NotFoundException(Error error) {
        this.error = error;
    }

    public Error getError() {
        return error;
    }
}
