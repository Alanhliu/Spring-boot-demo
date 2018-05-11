package com.example.demo.util;

import java.util.HashMap;

public class R extends HashMap<String, Object> {

    public R() {

    }

    public static R ok(Object o) {
        R r = new R();
        r.put("status", 200);
        r.put("response", o);
        return r;
    }

    public static R error() {
        return error(0, "未知异常");
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("message", msg);
        return r;
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
