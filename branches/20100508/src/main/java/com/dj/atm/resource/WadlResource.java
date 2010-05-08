package com.dj.atm.resource;

import com.sun.jersey.server.wadl.WadlApplicationContext;
import com.sun.jersey.spi.resource.Singleton;
import com.sun.research.ws.wadl.Application;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

@Produces({"application/xml"})
@Singleton
@Path("wadl")
public class WadlResource {

    private static final Logger LOGGER = Logger.getLogger(WadlResource.class.getName());

    private static final String XML_HEADERS = "com.sun.xml.bind.xmlHeaders";

    private WadlApplicationContext wadlContext;

    private Application application;

    private byte[] wadlXmlRepresentation;

    public WadlResource(@Context WadlApplicationContext wadlContext) {
        this.wadlContext = wadlContext;
        application = wadlContext.getApplication();
    }

    @GET
    public synchronized Response getWadl(@Context UriInfo uriInfo) {
        if (wadlXmlRepresentation == null) {
            if (application.getResources().getBase() == null) {
                application.getResources().setBase(uriInfo.getBaseUri().toString());
            }
            try {
                final Marshaller marshaller = wadlContext.getJAXBContext().createMarshaller();
                marshaller.setProperty(XML_HEADERS, "<?xml-stylesheet type='text/xsl' href='http://localhost:8080/atm-web/wadl_stylesheet.xsl'?>");
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                final ByteArrayOutputStream os = new ByteArrayOutputStream();
                marshaller.marshal(application, os);
                wadlXmlRepresentation = os.toByteArray();
                os.close();
            } catch (Exception e) {
                LOGGER.log(Level.WARNING, "Could not marshal wadl Application.", e);
                return javax.ws.rs.core.Response.ok(application).build();
            }
        }
        return Response.ok(new ByteArrayInputStream(wadlXmlRepresentation)).build();
    }
} 