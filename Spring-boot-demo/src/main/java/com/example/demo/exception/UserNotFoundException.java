package com.example.demo.exception;

public class UserNotFoundException extends RuntimeException {
    private long uid;

    public UserNotFoundException(long uid) {
        this.uid = uid;
    }

    public long getUid() {
        return uid;
    }
}
