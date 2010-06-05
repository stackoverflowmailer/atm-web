package com.dj.atm.resource;

import com.dj.atm.core.model.QueryParameter;
import com.dj.atm.core.model.User;
import com.dj.atm.core.util.WrappedResponse;
import com.dj.atm.user.service.UserService;
import com.google.inject.Inject;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Script Runner
 */
@Path("/user")
public class UserResource {
    private UserService userService;

    @Inject
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @Path("/save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Map save(User user) {
        User persisted = userService.saveUser(user);
        Map rValue = new HashMap();
        rValue.put("success", true);
        rValue.put("data", persisted);
        return rValue;
    }

    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public WrappedResponse<List<User>> getUsers(@Context HttpServletRequest request) {
        QueryParameter qp = HttpServletUtil.getQueryParameter(request);
        List<User> users = userService.getUsers(qp);
        WrappedResponse<List<User>> response = new WrappedResponse<List<User>>(true, users);
        return response;
    }

}
