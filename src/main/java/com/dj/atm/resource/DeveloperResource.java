package com.dj.atm.resource;

import com.dj.atm.core.model.QueryParameter;
import com.dj.atm.core.model.User;
import com.dj.atm.core.report.ReportGenerator;
import com.dj.atm.core.util.WrappedResponse;
import com.dj.atm.developer.model.Band;
import com.dj.atm.developer.model.Developer;
import com.dj.atm.developer.model.Name;
import com.dj.atm.developer.service.DeveloperService;
import com.google.inject.Inject;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.sun.jersey.core.header.ContentDisposition;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
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

    @POST
    @Produces({MediaType.APPLICATION_JSON, "text/json"})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/getDeveloper")
    public WrappedResponse<Developer> getDeveloper(@FormParam("id") String id) {
        Developer developer = developerService.getDeveloper(Long.valueOf(id));
        /*Developer developer = new Developer();
        developer.setId(1000L);
        developer.setName(new Name("Deepak", "", "Jacob"));
        developer.setBand(Band.B);
        developer.setDoj(new Date());
        developer.setBloodGroup("B+");
        */
        WrappedResponse<Developer> response = new WrappedResponse<Developer>(true, developer);
        return response;
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

    //@POST

    //@Produces({"application/pdf"})
    @GET
    //@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Path("/report")
    public Response generateReport(
            @Context HttpServletRequest request,
            @Context HttpServletResponse response,
            @Context ServletContext ctx) throws Exception {
        ContentDisposition cd =
                ContentDisposition.type("attachment").fileName("report.pdf").build();
       
        byte[] bytes = ReportGenerator.generateReport();
        return Response.ok(bytes).header("Content-Disposition", cd).type("application/pdf").build();

    }
}
