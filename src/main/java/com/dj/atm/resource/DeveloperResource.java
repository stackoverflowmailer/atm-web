package com.dj.atm.resource;

//~--- non-JDK imports --------------------------------------------------------

import com.dj.atm.core.model.QueryParameter;
import com.dj.atm.core.report.ReportGenerator;
import com.dj.atm.core.util.WrappedResponse;
import com.dj.atm.developer.model.Developer;
import com.dj.atm.developer.service.DeveloperService;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//~--- JDK imports ------------------------------------------------------------

import com.sun.jersey.core.header.ContentDisposition;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Developer resource class hosted at the URI path "/developer"
 */
@Path("/developer")
public class DeveloperResource {
    private static final String    CONTENT_DISPOSITION_MS_EXCEL = "application/vnd.ms-excel";
    private static final String    CONTENT_DISPOSITION_PDF      = "application/pdf";
    private static Log             logger                       = LogFactory.getLog(DeveloperResource.class);
    private final DeveloperService developerService;

    @Inject
    public DeveloperResource(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @POST
    @Produces({ MediaType.APPLICATION_JSON, "text/json" })
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/getDeveloper")
    public WrappedResponse<Developer> getDeveloper(@FormParam("id") String id) {
        Developer                  developer = developerService.getDeveloper(Long.valueOf(id));
        WrappedResponse<Developer> response  = new WrappedResponse<Developer>(true, developer);

        return response;
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON, "text/json" })
    @Path("/developers")
    public WrappedResponse<Collection> getDevelopers(@Context HttpServletRequest request) {
        QueryParameter        qp         = HttpServletUtil.getQueryParameter(request);
        List<Developer>       developers = developerService.getDevelopers(qp);
        Collection<Developer> filtered   = Collections2.filter(developers, new Predicate<Developer>() {
            @Override
            public boolean apply(Developer developer) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Developer  : " + developer.getId() + " : " + developer.getName());
                }

                if (developer.getName() == null) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Developer name found to null for : " + developer.getId());
                    }

                    return false;
                }

                return true;
            }
        });
        WrappedResponse<Collection> response = new WrappedResponse<Collection>(true, filtered);

        return response;
    }

    @Path("/saveDeveloper")
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Map saveDeveloper(Developer developer) {
        Developer persisted = developerService.save(developer);
        Map       rValue    = new HashMap();

        rValue.put("success", true);
        rValue.put("data", persisted);

        return rValue;
    }

    @POST
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Path("/report")
    public Response generateReport(@Context HttpServletRequest request, @Context HttpServletResponse response,
                                   @Context ServletContext ctx)
            throws Exception {
        QueryParameter     qp         = HttpServletUtil.getQueryParameter(request);
        List<Developer>    developers = developerService.getDevelopers(qp);
        ContentDisposition cd         = ContentDisposition.type("inline").fileName("developer-report.pdf").build();
        byte[]             bytes      = new ReportGenerator().generateReport(developers);

        return Response.ok(bytes).header("Content-Disposition", cd).type(CONTENT_DISPOSITION_PDF).build();
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
