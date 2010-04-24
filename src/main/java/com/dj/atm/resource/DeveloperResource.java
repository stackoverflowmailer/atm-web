package com.dj.atm.resource;

import com.dj.atm.core.model.QueryParameter;
import com.dj.atm.core.util.WrappedResponse;
import com.dj.atm.developer.model.Developer;
import com.dj.atm.developer.service.DeveloperService;
import com.google.inject.Inject;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
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
    public WrappedResponse<List> getDeveloper(@Context HttpServletRequest request) {
        /*Developer developer = new Developer();
        developer.setId(1000L);
        developer.setName(new Name("Deepak", "", "Jacob"));
        developer.setBand(Band.C);
        developer.setDoj(new Date());
        developer.setBloodGroup("A+");
        List<Developer> developers = new ArrayList<Developer>(1);
        developers.add(developer);*/
        QueryParameter qp = HttpServletUtil.getQueryParameter(request);
        List<Developer> developers = developerService.getDevelopers(qp);
        WrappedResponse<List> response = new WrappedResponse<List>(true, developers);
        return response;
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
