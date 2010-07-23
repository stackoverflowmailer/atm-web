package com.dj.atm.core.util;

//~--- non-JDK imports --------------------------------------------------------

import com.dj.atm.core.model.User;

//~--- JDK imports ------------------------------------------------------------

import javax.servlet.http.Cookie;

public class CookieUtil {
    public static Cookie createCookie(String cookieName, User user) {
        Cookie loginCookie = new Cookie(cookieName, user.getUsername());

        loginCookie.setMaxAge(3600);

        return loginCookie;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
