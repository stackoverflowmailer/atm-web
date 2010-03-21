package com.dj.atm.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.dj.atm.core.model.User;
import com.sun.jersey.core.util.MultivaluedMapImpl;

@Path("/login")
public class LoginResource {

    @POST
    @Produces( { MediaType.APPLICATION_JSON, "text/json" })
    @Consumes( { MediaType.APPLICATION_FORM_URLENCODED,
	    "application/x-www-form-urlencoded" })
    @Path("/login")
    public MultivaluedMap<String, String> login(User user) {
	MultivaluedMap<String, String> mMap = getMap(user);
	return mMap;
    }

    private MultivaluedMap<String, String> getMap(User u) {
	MultivaluedMap<String, String> mMap = new MultivaluedMapImpl();
	mMap.putSingle("username", u.getUsername());
	return mMap;
    }
}
