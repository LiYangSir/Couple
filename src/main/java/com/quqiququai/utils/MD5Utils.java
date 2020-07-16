package com.quqiququai.utils;

import org.springframework.util.DigestUtils;

public class MD5Utils {
    public static String code(String string) {
        return DigestUtils.md5DigestAsHex(string.getBytes());
    }

    public static void main(String[] args) {
        System.out.println(code("LY0115.."));
    }
}
