package com.dj.atm.resource;

import com.dj.atm.developer.model.Developer;
import com.dj.atm.developer.service.DeveloperService;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("java")
public class JavaResource {
    
    private String message;
    private final DeveloperService developerService;
    
    

    @Inject
    public JavaResource(@Named("message") String message, DeveloperService developerService) {

	this.message = message;
	this.developerService = developerService;
    }

    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Developer get() {

        System.out.println("From Java: " + message + developerService.hashCode() +" ..." + this.hashCode());
	Developer developer = new Developer();
	developer.setId(Long.valueOf(1000));
	return developer;
    }
    
    @PUT
    @Path("/post")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Developer post(Developer d) {

	//return "From Java: " + message + developerService.hashCode();
	Developer developer = new Developer();
	developer.setId(Long.valueOf(1000));
	return developer;
    }
    
}
