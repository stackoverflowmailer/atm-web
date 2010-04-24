package com.dj.atm.resource;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpServletUtil {

    public static void addCookieToResponse(String name,
                                           String content,
                                           HttpServletRequest request,
                                           HttpServletResponse response) {
        Cookie cookie = new Cookie(name, content);
        cookie.setMaxAge(-1);
        cookie.setPath(getWebCtxName(request));
        response.addCookie(cookie);
    }

    public static String getWebCtxName(HttpServletRequest request) {
        String ctxPath = request.getContextPath();
        return ctxPath;
    }


}