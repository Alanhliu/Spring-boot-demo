package com.example.demo.util;

import java.util.HashMap;
import java.util.Map;

public class Error {

    public static final int USER_NOT_FOUND = 10001;
    public static final int USER_NAME_OR_PWD_ERROR = 10002;
    public static final int USER_AUTH_FAILED = 1001;
    public static final int USER_AUTH_OTHER_DEVICE = 1002;

    public static Map errorMsgMap = new HashMap();

    private int code;
    private String message;

    public Error(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Error(int code) {
        this.code = code;
        this.message = errorMsgMap.get(code).toString();
    }

    public int getCode() {
        return code;
    }

    public String getMessage() { return message; }

    public static String getMessage(int code) { return errorMsgMap.get(code).toString(); }

    static {
        errorMsgMap.put(USER_NOT_FOUND,"USER_NOT_FOUND");
        errorMsgMap.put(USER_NAME_OR_PWD_ERROR,"USER_NAME_OR_PWD_ERROR");
        errorMsgMap.put(USER_AUTH_FAILED,"USER_AUTH_FAILED");
        errorMsgMap.put(USER_AUTH_OTHER_DEVICE,"USER_AUTH_OTHER_DEVICE");
    }
}
