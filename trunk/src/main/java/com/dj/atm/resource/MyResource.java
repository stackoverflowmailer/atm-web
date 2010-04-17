package com.dj.atm.resource;

import com.dj.atm.core.model.User;
import com.dj.atm.developer.model.Developer;
import com.dj.atm.developer.service.DeveloperService;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

/**
 * Example resource class hosted at the URI path "/myresource"
 */
@Path("/myresource")
public class MyResource {

    private DeveloperService developerService;

    /**
     * Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * 
     * @return String that will be send back as a response of type "text/plain".
     */
    @GET
    @Produces("text/plain")
    @Path("/getIt")
    public String getIt() {
	return "Hi there!";
    }

    /**
     * Returns a @see com.dj.atm.domain.User object based on the username
     * parameter.
     * 
     * @param username
     * @return
     */
    @GET
    @Produces( { MediaType.APPLICATION_JSON, "text/json" })
    @Path("/users")
    public User getUser(@QueryParam("username") String username) {
	return new User("test");
    }

    @POST
    @Produces( { MediaType.APPLICATION_JSON, "text/json" })
    @Consumes( { MediaType.APPLICATION_FORM_URLENCODED,
	    "application/x-www-form-urlencoded" })
    @Path("/developer/saveDeveloper")
    public MultivaluedMap<String, String> saveDeveloper(Developer developer) {
	Developer d = developerService.save(developer);
	MultivaluedMap<String, String> mMap = getMap(d);
	return mMap;
    }

    private MultivaluedMap<String, String> getMap(Developer d) {
	MultivaluedMap<String, String> mMap = new MultivaluedMapImpl();
	mMap.putSingle("firstName", d.getName().getFirstName());
	mMap.putSingle("lastName", d.getName().getLastName());
	return mMap;
    }

}
