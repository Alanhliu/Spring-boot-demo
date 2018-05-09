package com.example.demo.util;

public class Error {

    public static final int USER_NOT_FOUND = 10001;
    public static final int USER_NAME_OR_PWD_ERROR = 10002;

    private int code;
    private String message;

    public Error(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
