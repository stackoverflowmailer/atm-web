package com.dj.atm.resource;

import com.dj.atm.developer.model.Developer;
import com.dj.atm.developer.model.Name;
import com.dj.atm.developer.service.DeveloperService;
import com.google.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;


/**
 * Developer resource class hosted at the URI path "/developer"
 */
@Path("/developer")
public class DeveloperResource {
    private final DeveloperService developerService;

    @Inject
    public DeveloperResource(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, "text/json"})
    @Path("/developers")
    public Developer getDeveloper() {
        Developer developer = new Developer();
        developer.setName(new Name("Deepak", "", "Jacob"));
        return developer;
    }

    @Path("/saveDeveloper")
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Map saveDeveloper(
            Developer developer) {
        Developer persisted = developerService.save(developer);
        Map rValue = new HashMap();
        rValue.put("success", true);
        rValue.put("data", persisted);
        return rValue;
    }
}
