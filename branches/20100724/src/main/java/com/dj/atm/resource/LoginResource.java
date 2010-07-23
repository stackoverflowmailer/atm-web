package com.dj.atm.resource;

//~--- non-JDK imports --------------------------------------------------------

import com.dj.atm.core.model.User;
import com.dj.atm.core.util.WrappedResponse;

//~--- JDK imports ------------------------------------------------------------

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/login")
public class LoginResource {

    /* Name under which the user object is saved under Http Session scope */
    private static final String ATM_SESSION_KEY = "sessionKey";

    /**
     * Handles user log in requests. Expects username and password.
     *
     * @param username The user's sign-in name
     * @param password The user's password.
     * @param request  The injected HTTPServletRequest object.
     * @return The wrapped user object in a way front-end expects.
     */
    @POST
    @Produces({ MediaType.APPLICATION_JSON, "text/json" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Path("/login")
    public WrappedResponse<User> login(@FormParam("username") String username, @FormParam("password") String password,
                                       @Context HttpServletRequest request, @Context HttpServletResponse response) {
        User        user    = new User(username);
        HttpSession session = request.getSession(true);

        if (session != null) {
            session.setAttribute(ATM_SESSION_KEY, user);
        }

        HttpServletUtil.addCookieToResponse("atm-web", username, request, response);

        WrappedResponse<User> result = new WrappedResponse<User>(true, user);

        return result;
    }

    /**
     * Handles user log out requests.
     *
     * @param request The injected HTTPServletRequest object.
     * @return The wrapped user object in a way front-end expects.
     */
    @GET
    @Produces({ MediaType.APPLICATION_JSON, "text/json" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Path("/logout")
    public WrappedResponse<User> logout(@Context HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.removeAttribute(ATM_SESSION_KEY);
        }

        WrappedResponse<User> result = new WrappedResponse<User>(true, null);

        return result;
    }

    /**
     * If a valid session exists for the use then return the user object, else return null.
     *
     * @param request the HttpServletRequest object.
     * @return if a valid session exists the user object is returned, else return null.
     */
    @GET
    @Produces({ MediaType.APPLICATION_JSON, "text/json" })
    @Path("/check")
    public WrappedResponse<User> checkSession(@Context HttpServletRequest request) {
        HttpSession           session = request.getSession(false);
        User                  user    = (User) session.getAttribute(ATM_SESSION_KEY);
        WrappedResponse<User> result  = null;

        if (session != null) {
            if (null != user) {
                result = new WrappedResponse<User>(true, user);
            } else {
                result = new WrappedResponse<User>(false, null);
            }
        }

        return result;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
