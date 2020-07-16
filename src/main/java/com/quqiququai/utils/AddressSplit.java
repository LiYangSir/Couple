package com.quqiququai.utils;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressSplit {
    public static Map<String, String> addressSplit(String bottomUrls){
        String[] split = bottomUrls.split(";");
        Map<String, String> urls = new HashMap<>();
        for (String s : split) {
            String[] split1 = s.split(": ");
            urls.put(split1[0], split1[1]);
        }
        return urls;
    }

    public static void main(String[] args) throws InterruptedException {
        String str = "百度: https://wwww;Google: http://wwww";

        System.out.println(AddressSplit.addressSplit(str));

    }
}
