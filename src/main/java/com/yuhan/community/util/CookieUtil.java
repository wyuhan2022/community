package com.yuhan.community.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author yuhan
 * @create 2022-07-25 20:27
 */
public class CookieUtil {
    public static String getValue(HttpServletRequest request, String name){
        if (request == null || name == null) {
            throw new IllegalArgumentException("参数不能为空!");
        }
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
               return cookie.getValue();
            }
        }
        return null;
    }
}
