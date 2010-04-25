package com.dj.atm.core.security;

import com.dj.atm.core.model.User;
import com.google.inject.Singleton;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Responsible for checking authenticity of user before allowing responses to the AJAX calls.
 *
 * @author Script Runner
 */
@Singleton
public class AtmSecurityFilter implements Filter {

    private static Log logger = LogFactory.getLog(AtmSecurityFilter.class);

    /* Name under which the user object is saved in Http Session scope */
    private static final String ATM_SESSION_KEY = "sessionKey";
    /* Servlet Path for checking the validity of the session */
    private static final String SESSION_CHECK_REQUEST = "/webresources/login/check";
    /* Servlet Path for login request */
    private static final String LOGIN_REQUEST = "/webresources/login/login";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * Checks authenticity of user before making the AJAX calls.
     * <p/>
     * All the requests will be intercepted except request for
     * login and checking the validity of the session.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = ((HttpServletRequest) request).getSession(false);

        String requestedPath = httpRequest.getServletPath();
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("The servlet path %s", requestedPath));
        }
        // if this is a login request or session validity check then allow without any further actions.
        if (requestedPath.equals(SESSION_CHECK_REQUEST) || requestedPath.equals(LOGIN_REQUEST)) {
            chain.doFilter(request, response);
            return;
        }
        if (session != null) {
            User user = (User) session.getAttribute(ATM_SESSION_KEY);
            if (user != null) {
                // if a valid session exists and user object is found
                // then proceed for next actions
                chain.doFilter(request, response);
                return;
            }
            // user does not have a valid session
            handleAuthenticationFailure(request, response);
        }
        // user does not have a valid session
        handleAuthenticationFailure(request, response);
    }

    /**
     * In case a request is found without valid session then write the following Json response.
     */
    private void handleAuthenticationFailure(ServletRequest request, ServletResponse response) throws IOException {
        response.setContentType("text/json");
        response.getWriter().write("{ success : " + false + ", data : {message : 'Not a valid user' } }");
        //return;
    }

    @Override
    public void destroy() {

    }
}
