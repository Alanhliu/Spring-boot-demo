package com.example.demo.util;

import sun.security.provider.MD5;

import java.util.Date;

public class TokenUtil {

    public static String CreateToken(String username,int uid) {

        long time = new Date().getTime();
        String s = username + Integer.toString(uid) + time;
        try {
            String token = EncryptUtil.encoderByMd5(s);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
