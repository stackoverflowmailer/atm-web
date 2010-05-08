package com.dj.atm.resource;

import com.dj.atm.core.model.QueryParameter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HttpServletUtil {
    private static final String QUERY_PARAMETER = "QUERY_PARAMETER";

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


    public static QueryParameter getQueryParameter(HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);
        if (httpSession != null) {
            QueryParameter qp = (QueryParameter) httpSession.getAttribute(QUERY_PARAMETER);
            if (qp == null) {
                qp = new QueryParameter();
                httpSession.setAttribute(QUERY_PARAMETER, qp);
            }
            return qp;
        }
        return new QueryParameter();
    }
}