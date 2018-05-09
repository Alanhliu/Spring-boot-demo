package com.example.demo.exception;

import com.example.demo.util.Error;

public class UserException extends RuntimeException {



    public Error error;
    public UserException(Error error) {
        this.error = error;
    }

    public Error getError() {
        return error;
    }
}
