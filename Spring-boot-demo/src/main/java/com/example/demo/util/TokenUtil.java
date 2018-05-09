package com.example.demo.util;

import sun.security.provider.MD5;

public class TokenUtil {

    public static String CreateToken(String username,int uid) {

        String s = username + Integer.toString(uid);
        try {
            String token = EncryptUtil.encoderByMd5(s);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
