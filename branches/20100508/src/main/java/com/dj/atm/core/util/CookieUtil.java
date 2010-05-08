package com.dj.atm.core.util;

import com.dj.atm.core.model.User;

import javax.servlet.http.Cookie;

public class CookieUtil {

    public static Cookie createCookie(String cookieName, User user) {
        Cookie loginCookie = new Cookie(cookieName, user.getUsername());
        loginCookie.setMaxAge(3600);
        return loginCookie;
    }
}